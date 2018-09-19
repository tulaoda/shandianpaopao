package com.ssh.controller;

import com.ssh.entity.First;
import com.ssh.entity.Price;
import com.ssh.service.PriceService;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/price")
public class PriceController {
    @Autowired
    private PriceService priceService;

    @ApiImplicitParams({})
    @RequestMapping(value = "findAllPrice", method = RequestMethod.GET)
    @ResponseBody
    public Map findAllPrice() {
        Map map = new HashMap();
        List<Price> list = null;
        try {
            list = priceService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("content", list);
        map.put("msg", "执行成功！");
        return map;
    }
}
