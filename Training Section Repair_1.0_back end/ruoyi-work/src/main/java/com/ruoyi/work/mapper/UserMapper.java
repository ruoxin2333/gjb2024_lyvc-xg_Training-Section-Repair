package com.ruoyi.work.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.work.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 用户Mapper接口
 * 
 * @author SJY
 * @date 2024-03-12
 */

@Repository
public interface UserMapper extends BaseMapper<User>
{
        public User selectUserByLoginName(String userName);

}
