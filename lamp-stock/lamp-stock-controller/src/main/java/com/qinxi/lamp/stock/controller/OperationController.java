package com.qinxi.lamp.stock.controller;

import com.qinxi.lamp.stock.entity.Operation;
import com.qinxi.lamp.stock.operation.service.OperationService;
import com.qinxi.lamp.stock.vo.OperationPageQuery;
import com.qinxi.lamp.stock.vo.OperationResultVO;
import com.qinxi.lamp.stock.vo.OperationSaveVO;
import com.qinxi.lamp.stock.vo.OperationUpdateVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.interfaces.echo.EchoService;

/**
 * <p>
 * 前端控制器
 * 操作记录表
 * </p>
 *
 * @author qinxi
 * @date 2025-04-20 23:51:23
 * @create [2025-04-20 23:51:23] [qinxi] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/operation")
@Tag(name = "操作记录表")
public class OperationController extends SuperController<OperationService, Long, Operation
        , OperationSaveVO, OperationUpdateVO, OperationPageQuery, OperationResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @GetMapping(value = "test")
    public OperationResultVO test() {
        OperationResultVO operationResultVO = new OperationResultVO();
        operationResultVO.setId(1L);
        operationResultVO.setBuyDate(null);
        operationResultVO.setSellDate(null);
        operationResultVO.setStockCode("000001");
        operationResultVO.setStockName("上证指数");
        return operationResultVO;
    }

}


