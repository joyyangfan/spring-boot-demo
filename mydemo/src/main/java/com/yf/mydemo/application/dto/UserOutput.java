package com.yf.mydemo.application.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

@Data
public class UserOutput {
    /**
     * userId
     */
    private Long userId;
    /**
     * name
     */
    private String name;

    private String pwd;
}

