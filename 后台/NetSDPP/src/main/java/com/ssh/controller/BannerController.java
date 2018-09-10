package com.ssh.controller;

import com.ssh.entity.Banner;
import com.ssh.service.BannerService;
import com.ssh.utils.enums.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "banner")
@Controller
@RequestMapping(value = "/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "imgUrl", value = "图片地址", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "imgOrder", value = "轮播顺序", required = true, dataType = "Integer"),
            })
    @RequestMapping(value = "addBanner", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> addBanner(String imgUrl, Integer imgOrder) {
        Map<String, String> map = new HashMap<String, String>();
        Banner banner = new Banner();
        banner.setImgUrl(imgUrl);
        banner.setImgOrder(imgOrder);

        long flag = bannerService.save(banner);
        if (flag < 0) {
            map.put("msg", "执行成功！");
            return map;
        }
        map.put("msg", "执行失败！");
        return map;
    }


    @RequestMapping(value = "listBanner", method = RequestMethod.GET)
    @ResponseBody
    public Map listBanner() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Banner> list = bannerService.findAll();
//        if ()
            map.put("content", list);
        map.put("msg", ResultStatus.SUCCESS.getCode());
        return map;
    }

}
