package com.ruoyi.work.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.work.mapper.UserMapper;
import com.ruoyi.work.domain.User;
import com.ruoyi.work.service.IUserService;

/**
 * 用户Service业务层处理
 * 
 * @author SJY
 * @date 2024-03-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService
{
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    /**
     * 查询用户列表
     * 
     * @param user 用户
     * @return 用户
     */

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> selectUserList(User user)
    {
        LambdaQueryWrapper<User> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(user.getUsername())){
            lqw.like(User::getUsername ,user.getUsername());
        }
        return this.list(lqw);
    }


    @Override
    public User selectUserByLoginName(String userName)
    {
        return userMapper.selectUserByLoginName(userName);
    }

//    @Override
//    public User selectUserByLoginName(String userName)
//    {
//        return userMapper.selectUserByLoginName(userName);
//    }
}
