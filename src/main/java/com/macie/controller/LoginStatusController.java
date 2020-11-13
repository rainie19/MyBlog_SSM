package com.macie.controller;

import com.macie.dto.JsonResponse;
import com.macie.service.UserInfoService;
import com.macie.service.serviceImpl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Macie
 * @date 2021/10/10 -0:09
 */
@RestController
public class LoginStatusController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 登录
     * @param userName
     * @param password
     * @param httpSession
     */
    @RequestMapping("/login")
    public JsonResponse login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession httpSession) {
        String decodePwd = userInfoService.getPwdByUserName(userName);
        System.out.println(userName +".."+decodePwd);
        Map<String, Object> map = new HashMap();
        if(decodePwd != null && decodePwd.equals(password)) {
            map.put("token", password);
            httpSession.setAttribute("userName", userName);
            return JsonResponse.responseOK(map);
        }
        else {
            return JsonResponse.responseFailed("账号或密码错误");
        }
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    public JsonResponse logout() {
        return JsonResponse.responseOK();
    }

}
