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
import com.ruoyi.work.domain.User;
import com.ruoyi.work.service.IUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户Controller
 * 
 * @author SJY
 * @date 2024-03-12
 */
@Controller
@RequestMapping("/work/user")
public class UserController extends BaseController
{
    private String prefix = "work/user";

    @Autowired
    private IUserService userService;

    @RequiresPermissions("work:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询用户列表
     */
    @RequiresPermissions("work:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user)
    {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @RequiresPermissions("work:user:export")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(User user)
    {
        List<User> list = userService.selectUserList(user);
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("work:user:add")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(User user)
    {
        return toAjax(userService.save(user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        User user = userService.getById(id);
        mmap.put("user", user);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("work:user:edit")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(User user)
    {
        return toAjax(userService.updateById(user));
    }

    /**
     * 删除用户
     */
    @RequiresPermissions("work:user:remove")
    @Log(title = "用户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userService.removeByIds(Arrays.asList(Convert.toLongArray(ids))));
    }
}
