package com.cmb.okr.dao.domain.auth;

import com.cmb.okr.frame.db.BaseEntity;
import java.util.Date;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang
 * @date: 2018-02-21 03:47:22
 * @since 1.0.0
 */
public class UserAuth extends BaseEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 用户id
     */
    private String userId;

    /**
     * 权限id
     */
    private String authId;

    /**
     *
     */
    private String createId;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private String lastModifiedId;

    /**
     *
     */
    private Date lastModifiedTime;


    /**
     * 用户id
     */
    public void setUserId(String userId){
        this.userId = userId;
    }

    /**
     * 用户id
     */
    public String getUserId(){
        return  userId;
    }

    /**
     * 权限id
     */
    public void setAuthId(String authId){
        this.authId = authId;
    }

    /**
     * 权限id
     */
    public String getAuthId(){
        return  authId;
    }

    /**
     *
     */
    public void setCreateId(String createId){
        this.createId = createId;
    }

    /**
     *
     */
    public String getCreateId(){
        return  createId;
    }

    /**
     *
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
     *
     */
    public Date getCreateTime(){
        return  createTime;
    }

    /**
     *
     */
    public void setLastModifiedId(String lastModifiedId){
        this.lastModifiedId = lastModifiedId;
    }

    /**
     *
     */
    public String getLastModifiedId(){
        return  lastModifiedId;
    }

    /**
     *
     */
    public void setLastModifiedTime(Date lastModifiedTime){
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     *
     */
    public Date getLastModifiedTime(){
        return  lastModifiedTime;
    }
}