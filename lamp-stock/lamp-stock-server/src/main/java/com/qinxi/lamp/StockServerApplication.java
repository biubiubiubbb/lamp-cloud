package com.qinxi.lamp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import top.tangyh.basic.validator.annotation.EnableFormValidator;
import top.tangyh.lamp.common.ServerApplication;

import java.net.UnknownHostException;

import static top.tangyh.lamp.common.constant.BizConstant.BUSINESS_PACKAGE;
import static top.tangyh.lamp.common.constant.BizConstant.UTIL_PACKAGE;

/**
 * 股票模块启动类
 *
 * @author qinxi
 * @date 2025-04-20
 */
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {BUSINESS_PACKAGE, UTIL_PACKAGE})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@Slf4j
@EnableFormValidator
public class StockServerApplication extends ServerApplication {
    public static void main(String[] args) throws UnknownHostException {
        start(StockServerApplication.class, args);
    }
}
