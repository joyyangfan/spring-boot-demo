package com.yf.mydemo.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User extends AuditingEntity {
    /**
     * userId
     */
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id")
    private Long userId;
    /**
     * name
     */
    @TableField(value = "name")
    private String name;

    private String pwd;
}
