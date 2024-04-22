package com.ruoyi.work.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Result;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.jwt.utils.JwtUtils;
import com.ruoyi.work.domain.*;
import com.ruoyi.work.service.*;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SJY
 * @description 开发接口
 * @date 2021/11/19
 */
@RestController
@RequestMapping("/open")
@CrossOrigin
@Api(value = "前台接口控制器",tags={"公开访问接口"})
public class OpenController extends BaseController {
    /**
     * =======================================================
     * 接口案例开始
     * =======================================================
     */
    @ApiOperation("GET开发测试接口1")
    @GetMapping("/test1")
    public Result test1(){
        return Result.success("开发接口1");
    }

    @ApiOperation("GET开发测试接口2")
    @ApiImplicitParam(name = "parma1",value = "参数1",required = true)
    @GetMapping("/test2")
    public Result test2(String parma1){
        return Result.success("开发接口2",parma1);
    }

    @ApiOperation("GET开发测试接口3")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parma1",value = "参数1",required = true),
            @ApiImplicitParam(name = "parma2",value = "参数2",required = true)
    })
    @GetMapping("/test3")
    public Result test3(String parma1,String parma2){
        Map map = new HashMap();
        map.put("parma1",parma1);
        map.put("parma2",parma2);
        return Result.success("开发接口3",map);
    }

    @ApiOperation("GET开发测试接口4")
    @GetMapping("test4/{id}")
    @ApiImplicitParam(name = "id",value = "ID",paramType = "path",required = true)
    public Result test4(@PathVariable Integer id){
        return Result.success("开发接口4",id);
    }

    @ApiOperation("POST开发测试接口5")
    @PostMapping("test5")
    @ApiImplicitParam(name = "parma1",value = "参数1",required = true)
    //访问的时候必须设置content-type为application/x-www-form-urlencoded
    public Result test5(String parma1){
        return Result.success("开发接口5",parma1);
    }

    @ApiOperation("POST开发测试接口6")
    @PostMapping("test6")
    //访问的时候必须设置content-type为application/json
    public Result test5(@RequestBody Result result){
        return result;
    }

    @ApiOperation("POST开发测试接口7")
    @PostMapping("test7")
    //访问的时候必须设置content-type为application/json
    @ApiImplicitParam(name = "map",value = "参数1")
    public Result test7(@RequestBody Map map){
        return Result.success("开发接口7",map);
    }
    /**
     * =======================================================
     * 接口案例结束
     * =======================================================
     */


    @Autowired
    private ISafeService safeService;
    @PostMapping("/safe")
    @ApiOperation("维修")
    public Result safe (@RequestBody Safe safe){
        final boolean save = safeService.save(safe);
        if (save){
            return Result.success("添加成功");
        }else
            return Result.error();
    }



  @Autowired
  private ServerConfig serverConfig;
  @PostMapping("/upload")
  @ApiOperation("图片")
  @ResponseBody
  public AjaxResult uploadFile(MultipartFile file,@PathVariable(value = "projectPath", required = false) String projectPath) throws Exception
  {

      try {
          String fileName = FileUploadUtils.uploadOpenFile(file,projectPath);
          String url = serverConfig.getUrl() + fileName;
          AjaxResult ajax = AjaxResult.success();
          ajax.put("fileName", fileName);
          ajax.put("url", url);
          return ajax;
      }catch (IOException e) {
          return AjaxResult.error(e.getMessage());
      }
  }

  @Autowired
   private IDidianService didianService;
  @ApiOperation("地点")
    @GetMapping("/place")
    public Result getPlace(Didian didian){
     List<Didian> didians = didianService.selectDidianList(didian);
     return Result.success(didians);
  }



  @Autowired
  private IClassifyService classifyService;
    @ApiOperation("分类")
    @GetMapping("/classify")
    public Result getClassify(Classify classify){
        List<Classify> classifies = classifyService.selectClassifyList(classify);
        return Result.success(classifies);
    }



    @Autowired
    private IUserService userService;
    @ApiOperation("登录")
    @PostMapping("/login")
    public AjaxResult doLogin( @RequestBody User user) {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.lambda()
                .eq(User::getUsername, user.getUsername()) //判断用户名是否等于用户名
                .eq(User::getPassword, user.getPassword());
        List<User> users = userService.list(queryWrapper);
        if (users.size() == 1) { //成功
            User u = users.get(0);
            u.setPassword(null);
            String token = JwtUtils.createToken(u.getId().toString());
            return AjaxResult.success("登录成功").put("token",token);
        } else if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            return AjaxResult.error("用户名或密码不能为空");
        }
        else {
            return AjaxResult.error("用户名或密码错误");
        }
    }

    @ApiOperation("维修提交列表")
    @GetMapping("/getSafe")
    @CrossOrigin
    public Result getSafe(
            Integer cur//当前页码
    ) {
        if(cur==null){
            cur=1;
        }
        // 创建分页对象
        Page<Safe> page = new Page<>(cur, 5);
        // 创建查询条件
        QueryWrapper<Safe> queryWrapper = Wrappers.query();
        queryWrapper.lambda()
                .eq(Safe::getStatus, 1)
                .orderByAsc(Safe::getTime);

        IPage<Safe> list1 = safeService.page(page,queryWrapper);
        return Result.success(list1);
    }

  }






