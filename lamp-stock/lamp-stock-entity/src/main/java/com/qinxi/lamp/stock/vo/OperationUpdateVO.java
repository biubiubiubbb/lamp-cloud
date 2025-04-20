package com.qinxi.lamp.stock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.SuperEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 表单修改方法VO
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
@EqualsAndHashCode
@Builder
@Schema(description = "操作记录表")
public class OperationUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    @NotNull(message = "请填写主键ID", groups = SuperEntity.Update.class)
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
    @Size(max = 20, message = "股票代码长度不能超过{max}")
    private String stockCode;
    /**
     * 股票名称
     */
    @Schema(description = "股票名称")
    @Size(max = 50, message = "股票名称长度不能超过{max}")
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
    @Size(max = 65535, message = "买入原因长度不能超过{max}")
    private String buyReason;
    /**
     * 卖出原因
     */
    @Schema(description = "卖出原因")
    @Size(max = 65535, message = "卖出原因长度不能超过{max}")
    private String sellReason;
    /**
     * 总结
     */
    @Schema(description = "总结")
    @Size(max = 65535, message = "总结长度不能超过{max}")
    private String summary;
    /**
     * 模式
     */
    @Schema(description = "模式")
    @Size(max = 100, message = "模式长度不能超过{max}")
    private String strategy;
    /**
     * 是否模拟盘
     */
    @Schema(description = "是否模拟盘")
    private Boolean isSimulated;


}
