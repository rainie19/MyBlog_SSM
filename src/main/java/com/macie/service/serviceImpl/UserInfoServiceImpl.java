package com.macie.service.serviceImpl;

import com.macie.dao.UserInfoDao;
import com.macie.entity.UserInfo;
import com.macie.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Macie
 * @date 2020/9/30 -21:08
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    public UserInfoDao userInfoDao;

    @Override
    public UserInfo getUserInfoByUserName(String userName) {
        return userInfoDao.getUserInfoByUserName(userName);
    }

    @Override
    public String getPwdByUserName(String userName) {
        return userInfoDao.getPwdByUserName(userName);
    }

    @Override
    public int updateUserInfo(UserInfo userInfo, String oldUserName) {
        return userInfoDao.updateUserInfo(userInfo, oldUserName);
    }

    @Override
    public Boolean updatePassWord(String userName, String oldPwd, String newPwd) {
        String currentPwd = userInfoDao.getPwdByUserName(userName);
        if(currentPwd != null && currentPwd.equals(oldPwd)){
            userInfoDao.updatePwd(userName, newPwd);
            return true;
        }
        return false;
    }
}
