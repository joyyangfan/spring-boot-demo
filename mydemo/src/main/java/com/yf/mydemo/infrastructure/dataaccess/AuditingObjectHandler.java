package com.yf.mydemo.infrastructure.dataaccess;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yf.mydemo.domain.UserSession;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuditingObjectHandler implements MetaObjectHandler {
    public AuditingObjectHandler() {
    }
    @Autowired
    private UserSession userSession;
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "isDeleted", Integer.class, 0);
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "lastModifyTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "createName", String.class, userSession.getUserName());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "lastModifyName", String.class, userSession.getUserName());
    }
}
