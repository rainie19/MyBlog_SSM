package com.macie.controller;

import com.macie.common.CommonConstants;
import com.macie.dto.JsonResponse;
import com.macie.entity.UserInfo;
import com.macie.service.UserInfoService;
import com.macie.util.ImageUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Macie
 * @date 2021/10/9 -22:31
 */
@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取用户个人信息
     *
     * @param userName
     * @return
     */
    @RequestMapping("/getUserInfo")
    public JsonResponse getUserInfo(@RequestParam("userName") String userName) {
        Map<String, Object> map = new HashMap();
        UserInfo userInfo = userInfoService.getUserInfoByUserName(userName);
        map.put("userInfo", userInfo);
        return JsonResponse.responseOK(map);
    }

    /**
     * 修改用户个人信息
     *
     */
    @PostMapping("/modifyUserInfo")
    public void modifyUserInfo(UserInfo userInfo, @RequestParam("type") String type,
                               @RequestParam(value = "userAvatar", required = false) MultipartFile userAvatar,
                               @RequestParam(value = "oldUserName", required = false) String oldUserName,
                               @RequestParam(value = "oldPassWd", required = false) String oldPassWd,
                               @RequestParam(value = "newPassWd", required = false) String newPassWd) {
        System.out.println("modifyUserInfo:"+ userInfo);
        if("profile".equals(type)) {
            userInfoService.updateUserInfo(userInfo, oldUserName);
            if(userAvatar != null && !userAvatar.isEmpty()) {
                String fileName = userAvatar.getOriginalFilename();
                // 获取后缀名
                int index = fileName.lastIndexOf('.');
                String imgType = fileName.substring(index);
                ImageUploadUtil.saveImage(userAvatar, CommonConstants.IMAGE_UPLOAD_PATH_AVATAR, userInfo.getUserName()+imgType);
            }
        }
        else if("account".equals(type)) {
            userInfoService.updatePassWord(userInfo.getUserName(), oldPassWd, newPassWd);
        }

    }
}
