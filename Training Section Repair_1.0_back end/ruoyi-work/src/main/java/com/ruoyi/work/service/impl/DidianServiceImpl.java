package com.ruoyi.work.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruoyi.work.mapper.DidianMapper;
import com.ruoyi.work.domain.Didian;
import com.ruoyi.work.service.IDidianService;

/**
 * 地点Service业务层处理
 * 
 * @author SJY
 * @date 2024-03-11
 */
@Service
public class DidianServiceImpl extends ServiceImpl<DidianMapper, Didian> implements IDidianService
{
    private static final Logger log = LoggerFactory.getLogger(DidianServiceImpl.class);
    /**
     * 查询地点列表
     * 
     * @param didian 地点
     * @return 地点
     */
    @Override
    public List<Didian> selectDidianList(Didian didian)
    {
        LambdaQueryWrapper<Didian> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(didian.getName())){
            lqw.like(Didian::getName ,didian.getName());
        }
        return this.list(lqw);
    }
}
