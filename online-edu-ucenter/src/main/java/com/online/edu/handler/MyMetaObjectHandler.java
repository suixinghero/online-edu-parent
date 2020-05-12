package com.online.edu.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xujin
 * @package-name org.xujin.handler
 * @createtime 2020-03-30 16:47
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    //在mp执行添加操作时会执行
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
    //在mp执行更新操作时会执行
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
