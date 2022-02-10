package com.yf.mydemo.domain.repository;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yf.mydemo.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepository extends ServiceImpl<UserMapper, User> {
    public List<User> queryUsers(){
        return baseMapper.queryUsers();
    }
    public IPage<User> queryPageUserList(IPage page, @Param("ew") LambdaQueryWrapper<User> entityWrapper)
    {
        return baseMapper.queryPageUserList(page,entityWrapper);
    }

}
