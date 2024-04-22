package com.ruoyi.work.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 卫生对象 clean
 * 
 * @author SJY
 * @date 2024-03-27
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "Clean", description = "卫生实体")
public class Clean implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "ID",width = 3)
    @TableId(value = "id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Long id;

    /** 地点 */
    @Excel(name = "地点",width = 20)
    @ApiModelProperty("地点")
    private String place;

    /** 卫生描述 */
    @Excel(name = "卫生描述",width = 30)
    @ApiModelProperty("卫生描述")
    private String question;

    /** 图片 */
    @Excel(name = "图片",cellType = Excel.ColumnType.IMAGE,height = 30,width = 10)
    @ApiModelProperty("图片")
    private String pic;

    
    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("时间")
    private Date time;

    /** 提交人 */
    @Excel(name = "提交人")
    @ApiModelProperty("提交人")
    private String name;

    /** 联系方式 */
    @Excel(name = "联系方式")
    @ApiModelProperty("联系方式")
    private String phone;

    /** 状态 */
    @Excel(name = "状态")
    @ApiModelProperty("状态")
    private Integer status;

    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private Map<String, Object> params = new HashMap<>();
}