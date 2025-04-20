package com.qinxi.lamp.stock.vo;

import cn.hutool.core.map.MapUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.Entity;
import top.tangyh.basic.interfaces.echo.EchoVO;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 表单查询方法返回值VO
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
@Schema(description = "操作记录表")
public class OperationResultVO extends Entity<Integer> implements Serializable, EchoVO {

    @Serial
    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "主键ID")
    private Long id;

    /**
     * 买入日期
     */
    @Schema(description = "买入日期")
    private LocalDate buyDate;
    /**
     * 卖出日期
     */
    @Schema(description = "卖出日期")
    private LocalDate sellDate;
    /**
     * 股票代码
     */
    @Schema(description = "股票代码")
    private String stockCode;
    /**
     * 股票名称
     */
    @Schema(description = "股票名称")
    private String stockName;
    /**
     * 买入金额
     */
    @Schema(description = "买入金额")
    private BigDecimal buyAmount;
    /**
     * 收益
     */
    @Schema(description = "收益")
    private BigDecimal profit;
    /**
     * 类型 [1 买入 2卖出]
     */
    @Schema(description = "类型 [1 买入 2卖出]")
    private Integer orderSide;
    /**
     * 买入原因
     */
    @Schema(description = "买入原因")
    private String buyReason;
    /**
     * 卖出原因
     */
    @Schema(description = "卖出原因")
    private String sellReason;
    /**
     * 总结
     */
    @Schema(description = "总结")
    private String summary;
    /**
     * 模式
     */
    @Schema(description = "模式")
    private String strategy;
    /**
     * 是否模拟盘
     */
    @Schema(description = "是否模拟盘")
    private Boolean isSimulated;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;
}
