package com.ssh.controller;

import com.ssh.entity.Banner;
import com.ssh.entity.FormId;
import com.ssh.entity.User;
import com.ssh.service.FormIdService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Api(value = "formId")
@Controller
@RequestMapping(value = "/formId")
public class FormIdController {
    @Autowired
    private FormIdService formIdService;


    @RequestMapping(value = "findFormId", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> findFormId(String openId) {
        Map<String, String> map = new HashMap<String, String>();
        FormId formId = formIdService.findByOpenId(openId);
        map.put("fromId", formId.getFormId());
        map.put("msg", "200");
        return map;
    }

    @RequestMapping(value = "saveOrUpdateFormId", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, String> findFormId(@RequestBody FormId formId) {
        Map<String, String> map = new HashMap<String, String>();
        formIdService.saveOrUpdate(formId);
//        map.put("fromId", formId.getFormId());
        map.put("msg", "200");
        return map;
    }
}
