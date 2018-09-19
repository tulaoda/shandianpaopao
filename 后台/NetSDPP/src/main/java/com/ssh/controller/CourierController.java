package com.ssh.controller;

import com.ssh.entity.Banner;
import com.ssh.entity.User;
import com.ssh.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/courier")
public class CourierController {
    @Autowired
    private CourierService courierService;

    @RequestMapping(value = "judgeAdmin", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> judgeAdmin(String openId) {
        Map<String, String> map = new HashMap<String, String>();
        boolean flag = courierService.findCourierByOpenid(openId);
        if (flag == true) {
            map.put("code", "200");
            return map;
        }
        map.put("code", "-100");
        return map;
    }
}
