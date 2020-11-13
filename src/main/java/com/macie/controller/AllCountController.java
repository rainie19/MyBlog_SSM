package com.macie.controller;

import com.macie.dto.JsonResponse;
import com.macie.service.AllCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Macie
 * @date 2021/10/10 -0:17
 */
@RestController
public class AllCountController {

    @Autowired
    private AllCountService allCountService;

    @RequestMapping("/getAllCount")
    public JsonResponse getAllCount() {
        TreeMap<String, Long> countMap = allCountService.getAllCount();
        Map<String, Object> map = new HashMap();
        map.put("AllCount", countMap);
        return JsonResponse.responseOK(map);
    }

}
