package com.ssh.controller;

import java.util.HashMap;
import java.util.Map;

import com.ssh.entity.User;
import com.ssh.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Api(value = "user")
@Controller
@RequestMapping(value = "/user") // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {
    @Autowired
    private UserService userService;


    @ApiImplicitParams(
            {@ApiImplicitParam(name = "openid", value = "用户userid", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "school", value = "学校", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "telephone", value = "电话", required = true, dataType = "string")
            })
    @RequestMapping(value = "register", method = RequestMethod.GET)
    @ResponseBody
    public Map register(String openid,
                        String name,
                        String school,
                        String address,
                        String telephone) {
        Map map = new HashMap();
        User user;
        if (userService.getUserByOpenId(openid) == null) {
            user = new User();
            user.setOpenid(openid);
            user.setName(name);
            user.setSchool(school);
            user.setAddress(address);
            user.setTelephone(telephone);
            userService.save(user);
        } else {
            user = userService.getUserByOpenId(openid);
            user.setOpenid(openid);
            user.setName(name);
            user.setSchool(school);
            user.setAddress(address);
            user.setTelephone(telephone);
            userService.saveOrUpdate(user);
        }

        map.put("user", openid);
        return map;
    }


    @RequestMapping(value = "findUser", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> findUser(String openId) {
        Map<String, String> map = new HashMap<String, String>();
        User user = userService.getUserByOpenId(openId);
        if (user == null) {
            map.put("msg", "执行失败！");
            return map;
        }
        map.put("name", user.getName());
        map.put("tel", user.getTelephone());
        map.put("address", user.getAddress());
        map.put("msg", "200");
        return map;
    }


}
