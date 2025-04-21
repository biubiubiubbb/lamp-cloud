package com.qinxi.lamp.stock.gateway;

import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xi.qin
 * @date 2025/4/21 14:19
 */
@Slf4j
@Component
public class StockGatewayApiImpl implements StockGatewayApi {

    private static final String BASE_URL = "http://127.0.0.1:8080/api/public/";

    private Map<String, Quote> fastQuoteMap = new HashMap<>();

    private Map<String, Quote> allQuoteMap = new HashMap<>();

    private ThreadPoolExecutor threadPoolExecutor = ThreadUtil.newExecutor(10, 20);

    @Override
    public void init() {
        long start = System.currentTimeMillis();
        List<Quote> quotes = this.listQuote();
        log.info("初始化股票行情数据开始 step1，耗时={}ms", System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        Set<String> withoutRealQuoteCodeSet = new ConcurrentHashSet<>();
        AtomicInteger i = new AtomicInteger();
        for (Quote quote : quotes) {
            List<CompletableFuture<Void>> futureList = new ArrayList<>();
            futureList.add(CompletableFuture.runAsync(() -> {
                Quote realQuote = this.quote(quote.getCode());
                if (realQuote != null) {
                    quote.setLimitUp(realQuote.getLimitUp());
                    quote.setLimitDown(realQuote.getLimitDown());
                } else {
                    withoutRealQuoteCodeSet.add(quote.getCode());
                }
                System.out.println(i.getAndIncrement());
            }, threadPoolExecutor));
            CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();
        }
        log.info("初始化股票行情数据完成 step2，耗时={}ms withoutRealQuoteCodeSet:{} errorSize:{} size:{}", System.currentTimeMillis() - start, withoutRealQuoteCodeSet, withoutRealQuoteCodeSet.size(), quotes.size());
    }

    @Override
    public List<StockSimpleInfo> listStockInfo() {
        String json = HttpUtil.get(BASE_URL + "stock_info_a_code_name");
        List<StockSimpleInfo> stockSimpleInfos = JSON.parseArray(json, StockSimpleInfo.class);
        return stockSimpleInfos.stream()
                // 过滤掉ST
                .filter(stockSimpleInfo -> stockSimpleInfo.getCode().contains("ST"))
                .toList();
    }

    /**
     * stock_bid_ask_em
     * 目标地址: https://quote.eastmoney.com/sz000001.html
     */
    @Override
    public Quote quote(String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("symbol", code);
        String data = HttpUtil.get(BASE_URL + "stock_bid_ask_em", params);
        if ("{\"detail\":\"Not Found\"}".equals(data)) {
            return null;
        }
        return Quote.fromSingle(data);
    }

    /**
     * stock_zh_a_spot_em
     * 目标地址: https://quote.eastmoney.com/center/gridlist.html#hs_a_board
     */
    public List<Quote> listQuote() {
        String json = HttpUtil.get(BASE_URL + "stock_zh_a_spot_em");
        List<Quote> quotes = Quote.fromMultiple(json);
        return quotes.stream()
                // 过滤掉ST
                .filter(quote -> !quote.getName().contains("ST"))
                // 过滤掉北交所
                .filter(quote -> !(quote.getCode().startsWith("920") || quote.getCode().startsWith("8") || quote.getCode().startsWith("43")))
                .toList();
    }


    public static void main(String[] args) {
//        Map<String, Object> params = new HashMap<>();
//        for (int i = 0; i < 10000; i++) {
//            params.put("symbol", "000859");
//            long start = System.currentTimeMillis();
//            String json = HttpUtil.get(BASE_URL + "stock_bid_ask_em", params);
//            long end = System.currentTimeMillis();
//            System.out.println("第" + i + "次请求耗时=" + (end - start));
//            if (!JSONUtil.isJson(json)) {
//                System.out.println(json);
//            }
//        }
//        params.put("symbol","000859");
//        String json2 = HttpUtil.get(BASE_URL + "stock_bid_ask_em", params);
//
//        String json3 = HttpUtil.get(BASE_URL + "stock_zh_a_spot_em");
//
//        List<Quote> quotes = Quote.fromMultiple(json3);
//        Quote quote = Quote.fromSingle(json2);
//        System.out.println(json2);

        StockGatewayApi api = new StockGatewayApiImpl();
        api.init();

    }


    // # A股实时行情数据和历史行情数据
    // "stock_zh_a_spot"  # 新浪 A 股实时行情数据
    // "stock_zh_a_spot_em"  # 东财 A 股实时行情数据
    // "stock_kc_a_spot_em"  # 东财科创板实时行情数据
    // "stock_zh_b_spot_em"  # 东财 B 股实时行情数据
    // "stock_zh_a_daily"  #  A 股历史行情数据(日频)
    // "stock_zh_a_minute"  #  A 股分时历史行情数据(分钟)
    // # 科创板实时行情数据和历史行情数据
    // "stock_zh_kcb_spot"  # 科创板实时行情数据
    // "stock_zh_kcb_daily"  # 科创板历史行情数据(日频)
    // # 指数实时行情和历史行情
    // "stock_zh_index_daily"  # 股票指数历史行情数据
    // "stock_zh_index_daily_tx"  # 股票指数历史行情数据-腾讯
    // "stock_zh_index_daily_em"  # 股票指数历史行情数据-东方财富
    // "stock_zh_index_spot_sina"  # 股票指数实时行情数据-新浪
    // "stock_zh_index_spot_em"  # 股票指数实时行情数据-东财


}
