package com.macie.service;

import com.macie.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author Macie
 * @date 2020/11/2 -20:52
 */
public interface UserInfoService {

    /**
     * 获取用户信息
     *
     * @param userName
     * @return
     */
    public UserInfo getUserInfoByUserName(String userName);

    /**
     * 获取密码
     *
     * @param userName
     * @return
     */
    public String getPwdByUserName(String userName);

    /**
     * 更新用户信息
     *
     * @param userInfo
     * @param oldUserName
     * @return
     */
    public int updateUserInfo(UserInfo userInfo, String oldUserName);

    /**
     * 更新用户密码
     *
     * @param userName
     * @param oldPwd
     * @param newPwd
     * @return
     */
    Boolean updatePassWord(String userName, String oldPwd, String newPwd);
}
