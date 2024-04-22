package com.ruoyi.work.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseAPIController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Result;
import com.ruoyi.common.utils.file.ImageUtils;
import com.ruoyi.work.domain.Clean;
import com.ruoyi.work.service.ICleanService;
import com.ruoyi.work.service.ISafeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author SJY
 * @description
 * @date 2021/9/10
 */
@RestController
@RequestMapping("/api")
public class APIController extends BaseAPIController {
    @Autowired
    private ISafeService safeService;
    @Autowired
    private ICleanService cleanService;


    @GetMapping("/test")
    public AjaxResult test()
    {
        return AjaxResult.success("test success");
    }

    @PostMapping(value = { "/upload/{projectPath}", "/upload" })
    @ResponseBody
    public AjaxResult upload(@RequestParam("file") MultipartFile file,
                             @PathVariable(value = "projectPath", required = false) String projectPath){
        String fileName = uploadOpenFile(file,projectPath);

        if(fileName != null){
            return AjaxResult.success("上传图片成功", fileName);
        }
        return error("上传图片失败");
    }

    @GetMapping(value="/deleteImage")
    public AjaxResult deleteImage(String fileName){
        if(ImageUtils.deleteOpenFile(fileName)){
            return AjaxResult.success("删除图片成功", fileName);
        }
        return error("删除图片失败");
    }

    @ApiOperation("卫生提交列表")
    @GetMapping("/getClean")
    @CrossOrigin
    public Result getClean(
            Integer cur//当前页码
    ) {
        if(cur==null){
            cur=1;
        }
        // 创建分页对象
        Page<Clean> page = new Page<>(cur, 5);
        // 创建查询条件
        QueryWrapper<Clean> queryWrapper = Wrappers.query();
        queryWrapper.lambda()
                .eq(Clean::getStatus, 1)
                .orderByAsc(Clean::getTime);
        IPage<Clean> list = cleanService.page(page,queryWrapper);
        return Result.success(list);
    }


    @PostMapping("/clean")
    @ApiOperation("卫生")
    public Result Clean (@RequestBody Clean clean){
        final boolean save = cleanService.save(clean);
        if (save){
            return Result.success("添加成功");
        }else
            return Result.error();
    }


}
