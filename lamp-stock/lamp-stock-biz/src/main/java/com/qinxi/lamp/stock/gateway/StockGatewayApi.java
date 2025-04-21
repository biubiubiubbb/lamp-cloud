package com.qinxi.lamp.stock.gateway;

import java.util.List;

/**
 * @author xi.qin
 * @date 2025/4/21 14:09
 */
public interface StockGatewayApi {


    void init();

    List<StockSimpleInfo> listStockInfo();

    /**
     * 最新报价
     */
    Quote quote(String code);


}
