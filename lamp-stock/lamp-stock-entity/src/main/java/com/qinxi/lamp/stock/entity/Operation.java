package com.qinxi.lamp.stock.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.Entity;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.baomidou.mybatisplus.annotation.SqlCondition.EQUAL;
import static top.tangyh.lamp.model.constant.Condition.LIKE;


/**
 * <p>
 * 实体类
 * 操作记录表
 * </p>
 *
 * @author qinxi
 * @date 2025-04-20 23:51:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("operation")
public class Operation extends Entity<Long> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 买入日期
     */
    @TableField(value = "buy_date", condition = EQUAL)
    private LocalDate buyDate;
    /**
     * 卖出日期
     */
    @TableField(value = "sell_date", condition = EQUAL)
    private LocalDate sellDate;
    /**
     * 股票代码
     */
    @TableField(value = "stock_code", condition = LIKE)
    private String stockCode;
    /**
     * 股票名称
     */
    @TableField(value = "stock_name", condition = LIKE)
    private String stockName;
    /**
     * 买入金额
     */
    @TableField(value = "buy_amount", condition = EQUAL)
    private BigDecimal buyAmount;
    /**
     * 收益
     */
    @TableField(value = "profit", condition = EQUAL)
    private BigDecimal profit;
    /**
     * 类型 [1 买入 2卖出]
     */
    @TableField(value = "order_side", condition = EQUAL)
    private Integer orderSide;
    /**
     * 买入原因
     */
    @TableField(value = "buy_reason", condition = LIKE)
    private String buyReason;
    /**
     * 卖出原因
     */
    @TableField(value = "sell_reason", condition = LIKE)
    private String sellReason;
    /**
     * 总结
     */
    @TableField(value = "summary", condition = LIKE)
    private String summary;
    /**
     * 模式
     */
    @TableField(value = "strategy", condition = LIKE)
    private String strategy;
    /**
     * 是否模拟盘
     */
    @TableField(value = "is_simulated", condition = EQUAL)
    private Boolean isSimulated;



}
