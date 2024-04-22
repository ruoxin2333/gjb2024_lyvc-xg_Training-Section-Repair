package com.ruoyi.work.service;

import java.util.List;
import com.ruoyi.work.domain.Didian;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 地点Service接口
 * 
 * @author SJY
 * @date 2024-03-11
 */
public interface IDidianService extends IService<Didian> {
    /**
     * 查询地点列表
     * 
     * @param didian 地点
     * @return 地点集合
     */
    List<Didian> selectDidianList(Didian didian);
}
