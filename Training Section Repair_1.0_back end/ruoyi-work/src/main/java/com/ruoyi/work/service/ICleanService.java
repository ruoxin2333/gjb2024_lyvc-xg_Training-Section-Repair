package com.ruoyi.work.service;

import java.util.List;
import com.ruoyi.work.domain.Clean;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 卫生Service接口
 * 
 * @author SJY
 * @date 2024-03-27
 */
public interface ICleanService extends IService<Clean> {
    /**
     * 查询卫生列表
     * 
     * @param clean 卫生
     * @return 卫生集合
     */
    List<Clean> selectCleanList(Clean clean);
}
