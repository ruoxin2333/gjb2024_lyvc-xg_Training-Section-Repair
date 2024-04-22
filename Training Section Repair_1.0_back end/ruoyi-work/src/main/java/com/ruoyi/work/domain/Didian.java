package com.ruoyi.work.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.annotation.Excel;

/**
 * 地点对象 didian
 * 
 * @author SJY
 * @date 2024-03-11
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "Didian", description = "地点实体")
public class Didian implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "ID")
    @TableId(value = "id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    @ApiModelProperty("名称")
    private String name;

    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private Map<String, Object> params = new HashMap<>();
}
