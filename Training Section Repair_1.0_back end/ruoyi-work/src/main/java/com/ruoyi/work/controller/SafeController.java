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
import com.ruoyi.work.domain.Safe;
import com.ruoyi.work.service.ISafeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 维修Controller
 * 
 * @author SJY
 * @date 2024-03-25
 */
@Controller
@RequestMapping("/work/safe")
public class SafeController extends BaseController
{
    private String prefix = "work/safe";

    @Autowired
    private ISafeService safeService;

    @RequiresPermissions("work:safe:view")
    @GetMapping()
    public String safe()
    {
        return prefix + "/safe";
    }

    /**
     * 查询维修列表
     */
    @RequiresPermissions("work:safe:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Safe safe)
    {
        startPage();
        List<Safe> list = safeService.selectSafeList(safe);
        return getDataTable(list);
    }

    /**
     * 导出维修列表
     */
    @RequiresPermissions("work:safe:export")
    @Log(title = "维修", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Safe safe)
    {
        List<Safe> list = safeService.selectSafeList(safe);
        ExcelUtil<Safe> util = new ExcelUtil<Safe>(Safe.class);
        return util.exportExcel(list, "safe");
    }

    /**
     * 新增维修
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存维修
     */
    @RequiresPermissions("work:safe:add")
    @Log(title = "维修", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Safe safe)
    {
        return toAjax(safeService.save(safe));
    }

    /**
     * 修改维修
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        Safe safe = safeService.getById(id);
        mmap.put("safe", safe);
        return prefix + "/edit";
    }

    /**
     * 修改保存维修
     */
    @RequiresPermissions("work:safe:edit")
    @Log(title = "维修", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Safe safe)
    {
        return toAjax(safeService.updateById(safe));
    }

    /**
     * 删除维修
     */
    @RequiresPermissions("work:safe:remove")
    @Log(title = "维修", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(safeService.removeByIds(Arrays.asList(Convert.toLongArray(ids))));
    }
}
