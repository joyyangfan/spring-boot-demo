package com.yf.mydemo.domain.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yf.mydemo.domain.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    /**
     * 获取不包含按钮的菜单列表
     */
    List<User> queryUsers();
    IPage<User> queryPageUserList(IPage page, @Param("ew") LambdaQueryWrapper<User> entityWrapper);

}
