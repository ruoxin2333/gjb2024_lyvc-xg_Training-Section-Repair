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
import com.ruoyi.work.domain.Clean;
import com.ruoyi.work.service.ICleanService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 卫生Controller
 * 
 * @author SJY
 * @date 2024-03-27
 */
@Controller
@RequestMapping("/work/clean")
public class CleanController extends BaseController
{
    private String prefix = "work/clean";

    @Autowired
    private ICleanService cleanService;

    @RequiresPermissions("work:clean:view")
    @GetMapping()
    public String clean()
    {
        return prefix + "/clean";
    }

    /**
     * 查询卫生列表
     */
    @RequiresPermissions("work:clean:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Clean clean)
    {
        startPage();
        List<Clean> list = cleanService.selectCleanList(clean);
        return getDataTable(list);
    }

    /**
     * 导出卫生列表
     */
    @RequiresPermissions("work:clean:export")
    @Log(title = "卫生", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Clean clean)
    {
        List<Clean> list = cleanService.selectCleanList(clean);
        ExcelUtil<Clean> util = new ExcelUtil<Clean>(Clean.class);
        return util.exportExcel(list, "clean");
    }

    /**
     * 新增卫生
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存卫生
     */
    @RequiresPermissions("work:clean:add")
    @Log(title = "卫生", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Clean clean)
    {
        return toAjax(cleanService.save(clean));
    }

    /**
     * 修改卫生
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Clean clean = cleanService.getById(id);
        mmap.put("clean", clean);
        return prefix + "/edit";
    }

    /**
     * 修改保存卫生
     */
    @RequiresPermissions("work:clean:edit")
    @Log(title = "卫生", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Clean clean)
    {
        return toAjax(cleanService.updateById(clean));
    }

    /**
     * 删除卫生
     */
    @RequiresPermissions("work:clean:remove")
    @Log(title = "卫生", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cleanService.removeByIds(Arrays.asList(Convert.toLongArray(ids))));
    }
}
