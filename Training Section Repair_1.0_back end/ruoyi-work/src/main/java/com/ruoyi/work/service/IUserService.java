package com.ruoyi.work.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.work.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户Service接口
 * 
 * @author SJY
 * @date 2024-03-12
 */
public interface IUserService extends IService<User> {
    /**
     * 查询用户列表
     * 
     * @param user 用户
     * @return 用户集合
     */

    List<User> selectUserList(User user);


    public User selectUserByLoginName(String userName);
}
