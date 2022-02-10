package com.yf.mydemo.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
@Data
public class AuditingEntity {
    @TableField(value = "createName" ,fill = FieldFill.INSERT)
    private String createName;
    @TableField(value = "lastModifyName" ,fill = FieldFill.INSERT_UPDATE)
    private String lastModifyName;

    @TableLogic(value = "0", delval = "1")
    @TableField(value = "isDeleted" ,fill = FieldFill.INSERT)
    private Integer isDeleted;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "lastModifyTime",fill = FieldFill.INSERT_UPDATE)
    private Date lastModifyTime;

}
