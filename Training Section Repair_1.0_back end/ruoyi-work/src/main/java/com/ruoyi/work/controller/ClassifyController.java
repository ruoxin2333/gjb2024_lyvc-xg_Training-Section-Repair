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
import com.ruoyi.work.domain.Classify;
import com.ruoyi.work.service.IClassifyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 分类Controller
 * 
 * @author SJY
 * @date 2024-04-02
 */
@Controller
@RequestMapping("/work/classify")
public class ClassifyController extends BaseController
{
    private String prefix = "work/classify";

    @Autowired
    private IClassifyService classifyService;

    @RequiresPermissions("work:classify:view")
    @GetMapping()
    public String classify()
    {
        return prefix + "/classify";
    }

    /**
     * 查询分类列表
     */
    @RequiresPermissions("work:classify:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Classify classify)
    {
        startPage();
        List<Classify> list = classifyService.selectClassifyList(classify);
        return getDataTable(list);
    }

    /**
     * 导出分类列表
     */
    @RequiresPermissions("work:classify:export")
    @Log(title = "分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Classify classify)
    {
        List<Classify> list = classifyService.selectClassifyList(classify);
        ExcelUtil<Classify> util = new ExcelUtil<Classify>(Classify.class);
        return util.exportExcel(list, "classify");
    }

    /**
     * 新增分类
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存分类
     */
    @RequiresPermissions("work:classify:add")
    @Log(title = "分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Classify classify)
    {
        return toAjax(classifyService.save(classify));
    }

    /**
     * 修改分类
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Classify classify = classifyService.getById(id);
        mmap.put("classify", classify);
        return prefix + "/edit";
    }

    /**
     * 修改保存分类
     */
    @RequiresPermissions("work:classify:edit")
    @Log(title = "分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Classify classify)
    {
        return toAjax(classifyService.updateById(classify));
    }

    /**
     * 删除分类
     */
    @RequiresPermissions("work:classify:remove")
    @Log(title = "分类", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(classifyService.removeByIds(Arrays.asList(Convert.toLongArray(ids))));
    }
}
