package com.ruoyi.work.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruoyi.work.mapper.CleanMapper;
import com.ruoyi.work.domain.Clean;
import com.ruoyi.work.service.ICleanService;

/**
 * 卫生Service业务层处理
 * 
 * @author SJY
 * @date 2024-03-27
 */
@Service
public class CleanServiceImpl extends ServiceImpl<CleanMapper, Clean> implements ICleanService
{
    private static final Logger log = LoggerFactory.getLogger(CleanServiceImpl.class);
    /**
     * 查询卫生列表
     * 
     * @param clean 卫生
     * @return 卫生
     */
    @Override
    public List<Clean> selectCleanList(Clean clean)
    {
        LambdaQueryWrapper<Clean> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(clean.getPlace())){
            lqw.eq(Clean::getPlace ,clean.getPlace());
        }
        if (StringUtils.isNotBlank(clean.getQuestion())){
            lqw.like(Clean::getQuestion ,clean.getQuestion());
        }
        if (clean.getTime() != null){
            lqw.eq(Clean::getTime ,clean.getTime());
        }
        if (StringUtils.isNotBlank(clean.getName())){
            lqw.like(Clean::getName ,clean.getName());
        }
        if (clean.getStatus() != null){
            lqw.eq(Clean::getStatus ,clean.getStatus());
        }
        return this.list(lqw);
    }
}
