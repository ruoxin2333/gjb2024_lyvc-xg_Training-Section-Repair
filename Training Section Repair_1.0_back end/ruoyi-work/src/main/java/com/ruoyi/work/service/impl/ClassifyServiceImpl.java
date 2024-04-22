package com.ruoyi.work.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruoyi.work.mapper.ClassifyMapper;
import com.ruoyi.work.domain.Classify;
import com.ruoyi.work.service.IClassifyService;

/**
 * 分类Service业务层处理
 * 
 * @author SJY
 * @date 2024-04-02
 */
@Service
public class ClassifyServiceImpl extends ServiceImpl<ClassifyMapper, Classify> implements IClassifyService
{
    private static final Logger log = LoggerFactory.getLogger(ClassifyServiceImpl.class);
    /**
     * 查询分类列表
     * 
     * @param classify 分类
     * @return 分类
     */
    @Override
    public List<Classify> selectClassifyList(Classify classify)
    {
        LambdaQueryWrapper<Classify> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(classify.getName())){
            lqw.like(Classify::getName ,classify.getName());
        }
        return this.list(lqw);
    }
}
