package com.ruoyi.work.service;

import java.util.List;
import com.ruoyi.work.domain.Classify;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 分类Service接口
 * 
 * @author SJY
 * @date 2024-04-02
 */
public interface IClassifyService extends IService<Classify> {
    /**
     * 查询分类列表
     * 
     * @param classify 分类
     * @return 分类集合
     */
    List<Classify> selectClassifyList(Classify classify);
}
