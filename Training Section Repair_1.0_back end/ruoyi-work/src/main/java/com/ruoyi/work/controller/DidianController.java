package com.ruoyi.work.controller;

import java.util.List;
import java.util.Arrays;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.work.domain.Didian;
import com.ruoyi.work.service.IDidianService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地点Controller
 * 
 * @author SJY
 * @date 2024-03-11
 */
@Controller
@RequestMapping("/work/didian")
public class DidianController extends BaseController
{
    private String prefix = "work/didian";

    @Autowired
    private IDidianService didianService;

    @RequiresPermissions("work:didian:view")
    @GetMapping()
    public String didian()
    {
        return prefix + "/didian";
    }

    /**
     * 查询地点列表
     */
    @RequiresPermissions("work:didian:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Didian didian)
    {
        startPage();
        List<Didian> list = didianService.selectDidianList(didian);
        return getDataTable(list);
    }

    /**
     * 导出地点列表
     */
    @RequiresPermissions("work:didian:export")
    @Log(title = "地点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Didian didian)
    {
        List<Didian> list = didianService.selectDidianList(didian);
        ExcelUtil<Didian> util = new ExcelUtil<Didian>(Didian.class);
        return util.exportExcel(list, "didian");
    }

    /**
     * 新增地点
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存地点
     */
    @RequiresPermissions("work:didian:add")
    @Log(title = "地点", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Didian didian)
    {
        return toAjax(didianService.save(didian));
    }

    /**
     * 修改地点
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Didian didian = didianService.getById(id);
        mmap.put("didian", didian);
        return prefix + "/edit";
    }

    /**
     * 修改保存地点
     */
    @RequiresPermissions("work:didian:edit")
    @Log(title = "地点", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Didian didian)
    {
        return toAjax(didianService.updateById(didian));
    }

    /**
     * 删除地点
     */
    @RequiresPermissions("work:didian:remove")
    @Log(title = "地点", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(didianService.removeByIds(Arrays.asList(Convert.toLongArray(ids))));
    }
}
