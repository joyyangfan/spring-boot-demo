package com.yf.mydemo.interfaces;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yf.mydemo.application.dto.UserInput;
import com.yf.mydemo.application.dto.UserOutput;
import com.yf.mydemo.domain.entity.User;
import com.yf.mydemo.domain.event.MyEventData;
import com.yf.mydemo.domain.repository.UserRepository;
import com.yf.mydemo.infrastructure.authentication.AuthIgnore;
import com.yf.mydemo.infrastructure.eventbus.SampleEventBus;
import com.yf.mydemo.infrastructure.resp.PagedResult;
import com.yf.mydemo.infrastructure.resp.RespBody;
import lombok.AllArgsConstructor;
import lombok.var;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.swagger.annotations.Api;

@RestController
@AllArgsConstructor
@RequestMapping("/system/usermanage")
@Api(tags = "1.0", description = "用户管理", value = "用户管理")
public class HomeController {
    @Autowired
    SampleEventBus sampleEventBus;
    @Autowired
    Mapper mapper;

    @Resource
    private UserRepository userRepository;
    /**
     * 用户列表
     */
    @ApiOperation("获取用户列表")// 为每个handler添加方法功能描述
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public RespBody<List<UserOutput>> list(){
        //查询列表数据
        List<User> users = userRepository.queryUsers();
        List<UserOutput> data=new ArrayList<>();
        var output = mapper.map(users,data.getClass());
        RespBody<List<UserOutput>> respBody= RespBody.ok(output);
        return respBody;
    }
    /**
     * 用户列表
     */
    @ApiOperation("获取用户列表")// 为每个handler添加方法功能描述
    @RequestMapping(value = "/pagelist",method = RequestMethod.GET)
    public RespBody<PagedResult<UserOutput>> pagelist(){
        var wrapper=new QueryWrapper<User>().lambda().eq(User::getName,"222");
        //查询列表数据
        IPage<User> page = userRepository.queryPageUserList(new Page<User>(1,5),wrapper);
        List<UserOutput> data=new ArrayList<>();
        var datas = mapper.map(page.getRecords(),data.getClass());
        PagedResult<UserOutput> outputs = new PagedResult<>(datas, page.getTotal(), page.getCurrent(), page.getSize());
        RespBody<PagedResult<UserOutput>> respBody= RespBody.ok(outputs);
        return respBody;
    }
    /**
     * 用户详情
     */
    @ApiOperation("获取用户详情")// 为每个handler添加方法功能描述
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public RespBody<UserOutput> userDetail(){
        //查询列表数据
        List<User> users = userRepository.queryUsers();
        User user = users.stream().findFirst().get();
        var output = mapper.map(user,UserOutput.class);
        RespBody<UserOutput> respBody= RespBody.ok(output);
        return respBody;
    }
    /**
     * 用户详情
     */
    @AuthIgnore
    @ApiOperation("获取用户详情")// 为每个handler添加方法功能描述
    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    public Callable<RespBody<String>> userDetail(@RequestBody @Validated UserInput user){
        return new Callable<RespBody<String>>() {
            @Override
            public RespBody<String> call() throws Exception {
                //查询列表数据
                var userEntity = mapper.map(user,User.class);
                userRepository.save(userEntity);
                RespBody<String> respBody= RespBody.ok("ok");
                return respBody;
            }
        };
    }
    /**
     * 用户详情
     */
    @ApiOperation("获取用户详情")// 为每个handler添加方法功能描述
    @RequestMapping(value = "/detail",method = RequestMethod.PUT)
    public RespBody<String> updateDetail(UserInput user){
        //查询列表数据
        var userEntity = mapper.map(user,User.class);
        userRepository.saveOrUpdate(userEntity);
        RespBody<String> respBody= RespBody.ok("ok");
        return respBody;
    }
    /**
     * 用户详情
     */
    @AuthIgnore
    @ApiOperation("获取用户详情")// 为每个handler添加方法功能描述
    @RequestMapping(value = "/detail/{id}",method = RequestMethod.DELETE)
    public RespBody<String> detailUser(@PathVariable("id") Integer id){
        userRepository.removeById(id);
        RespBody<String> respBody= RespBody.ok("ok");
        sampleEventBus.post(new MyEventData("223"));
        return respBody;
    }
}
