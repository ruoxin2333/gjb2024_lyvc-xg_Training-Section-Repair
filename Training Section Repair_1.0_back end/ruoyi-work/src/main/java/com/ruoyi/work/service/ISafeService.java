package com.ruoyi.work.service;

import java.util.List;
import com.ruoyi.work.domain.Safe;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 维修Service接口
 * 
 * @author SJY
 * @date 2024-03-25
 */
public interface ISafeService extends IService<Safe> {
    /**
     * 查询维修列表
     * 
     * @param safe 维修
     * @return 维修集合
     */
    List<Safe> selectSafeList(Safe safe);
}
