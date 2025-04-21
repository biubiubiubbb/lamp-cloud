package com.qinxi.lamp.stock.gateway;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xi.qin
 * @date 2025/4/21 15:17
 */
@Data
public class Quote {

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 最新价格
     */
    private BigDecimal latest;

    /**
     * 均价
     */
    private BigDecimal averagePrice;

    /**
     * 涨幅
     */
    private BigDecimal increase;

    /**
     * 涨跌
     */
    private BigDecimal change;

    /**
     * 总手
     */
    private BigDecimal totalVolume;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 换手
     */
    private BigDecimal turnoverRate;

    /**
     * 量比
     */
    private BigDecimal volumeRatio;

    /**
     * 最高
     */
    private BigDecimal highest;

    /**
     * 最低
     */
    private BigDecimal lowest;

    /**
     * 今开
     */
    private BigDecimal open;

    /**
     * 昨收
     */
    private BigDecimal previousClose;

    /**
     * 涨停
     */
    private BigDecimal limitUp;

    /**
     * 跌停
     */
    private BigDecimal limitDown;

    /**
     * 涨速
     */
    private BigDecimal risingSpeed;

    /**
     * 五分钟涨跌
     */
    private BigDecimal fiveMinuteChange;

    /**
     * 流通市值
     */
    private BigDecimal flowMarketValue;

    public static Quote fromSingle(String quoteStr) {
        List<Map<String, Object>> list = JSON.parseObject(quoteStr, List.class);
        // 创建 Quote 对象
        var quote = new Quote();

        // 遍历解析结果并填充 Quote 对象
        for (var map : list) {
            var item = (String) map.get("item");
            String valueStr = map.get("value").toString();
            if (!NumberUtil.isNumber(valueStr)) {
                continue;
            }
            var value = new BigDecimal(valueStr);
            switch (item) {
                case "最新" -> quote.setLatest(value);
                case "均价" -> quote.setAveragePrice(value);
                case "涨幅" -> quote.setIncrease(value);
                case "涨跌" -> quote.setChange(value);
                case "总手" -> quote.setTotalVolume(value);
                case "金额" -> quote.setAmount(value);
                case "换手" -> quote.setTurnoverRate(value);
                case "量比" -> quote.setVolumeRatio(value);
                case "最高" -> quote.setHighest(value);
                case "最低" -> quote.setLowest(value);
                case "今开" -> quote.setOpen(value);
                case "昨收" -> quote.setPreviousClose(value);
                case "涨停" -> quote.setLimitUp(value);
                case "跌停" -> quote.setLimitDown(value);
            }
        }
        return quote;
    }


    public static List<Quote> fromMultiple(String quoteStr) {
        JSONArray array = JSON.parseArray(quoteStr);
        List<Quote> quotes = new ArrayList<>(array.size());
        for (Object o : array) {
            JSONObject jsonObject = JSON.parseObject(o.toString());
            Quote quote = new Quote();
            for (String key : jsonObject.keySet()) {
                String valueStr = jsonObject.get(key).toString();
                if ("代码".equals(key)) {
                    quote.setCode(valueStr);
                    continue;
                }
                if ("名称".equals(key)) {
                    quote.setName(valueStr);
                    continue;
                }
                if (!NumberUtil.isNumber(valueStr)) {
                    continue;
                }
                BigDecimal value = new BigDecimal(valueStr);
                switch (key) {
                    case "最新价" -> quote.setLatest(value);
                    case "均价" -> quote.setAveragePrice(value);
                    case "涨跌幅" -> quote.setIncrease(value);
                    case "涨跌额" -> quote.setChange(value);
                    case "成交量" -> quote.setTotalVolume(value);
                    case "成交额" -> quote.setAmount(value);
                    case "换手率" -> quote.setTurnoverRate(value);
                    case "量比" -> quote.setVolumeRatio(value);
                    case "最高" -> quote.setHighest(value);
                    case "最低" -> quote.setLowest(value);
                    case "今开" -> quote.setOpen(value);
                    case "昨收" -> quote.setPreviousClose(value);
                    case "5分钟涨跌" -> quote.setFiveMinuteChange(value);
                    case "流通市值" -> quote.setFlowMarketValue(value);
                }
            }
            quotes.add(quote);
        }
        return quotes;
    }
}
