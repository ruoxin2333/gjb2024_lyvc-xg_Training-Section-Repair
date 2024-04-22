package com.ruoyi.work.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruoyi.work.mapper.SafeMapper;
import com.ruoyi.work.domain.Safe;
import com.ruoyi.work.service.ISafeService;

/**
 * 维修Service业务层处理
 * 
 * @author SJY
 * @date 2024-03-25
 */
@Service
public class SafeServiceImpl extends ServiceImpl<SafeMapper, Safe> implements ISafeService
{
    private static final Logger log = LoggerFactory.getLogger(SafeServiceImpl.class);
    /**
     * 查询维修列表
     * 
     * @param safe 维修
     * @return 维修
     */
    @Override
    public List<Safe> selectSafeList(Safe safe)
    {
        LambdaQueryWrapper<Safe> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(safe.getPlace())){
            lqw.eq(Safe::getPlace ,safe.getPlace());
        }
        if (StringUtils.isNotBlank(safe.getType())){
            lqw.eq(Safe::getType ,safe.getType());
        }
        if (safe.getTime() != null){
            lqw.eq(Safe::getTime ,safe.getTime());
        }
        if (StringUtils.isNotBlank(safe.getName())){
            lqw.like(Safe::getName ,safe.getName());
        }
        if (safe.getStatus() != null){
            lqw.eq(Safe::getStatus ,safe.getStatus());
        }
        return this.list(lqw);
    }
}
