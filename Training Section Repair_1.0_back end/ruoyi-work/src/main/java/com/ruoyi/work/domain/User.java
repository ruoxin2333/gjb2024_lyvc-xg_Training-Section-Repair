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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 用户对象 user
 * 
 * @author SJY
 * @date 2024-03-12
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "User", description = "用户实体")
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    @TableId(value = "id", type= IdType.AUTO)
    @ApiModelProperty("id")
    private Integer id;

    /** 用户名 */
    @Excel(name = "用户名")
    @ApiModelProperty("用户名")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    @ApiModelProperty("密码")
    private String password;



    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private Map<String, Object> params = new HashMap<>();

//    public String getLoginName() {
//        return null;
//    }
//
//    @NotBlank(message = "登录账号不能为空")
//    @Size(min = 0, max = 30, message = "登录账号长度不能超过30个字符")
//    public String getLoginName() {
//        return username;
//    }
}
