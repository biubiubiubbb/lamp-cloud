package com.qinxi.lamp.stock.operation.manager.impl;

import com.qinxi.lamp.stock.entity.Operation;
import com.qinxi.lamp.stock.operation.manager.OperationManager;
import com.qinxi.lamp.stock.operation.mapper.OperationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;


/**
 * <p>
 * 通用业务实现类
 * 操作记录表
 * </p>
 *
 * @author qinxi
 * @date 2025-04-20 23:51:23
 * @create [2025-04-20 23:51:23] [qinxi] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OperationManagerImpl extends SuperManagerImpl<OperationMapper, Operation> implements OperationManager {

}


