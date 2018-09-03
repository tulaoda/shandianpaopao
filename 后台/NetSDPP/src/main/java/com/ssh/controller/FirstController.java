package com.ssh.controller;

import com.ssh.entity.First;
import com.ssh.service.FirstService;
import com.ssh.utils.CreateOrderID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Api(value = "first")
@Controller
@RequestMapping(value = "/first")
public class FirstController {
    @Autowired
    private FirstService firstService;

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "receiver", value = "收件人", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "telephone", value = "电话", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "information", value = "快递信息", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "size", value = "尺寸", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "deliveryTime", value = "送货时间段", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "classification", value = "分类", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "receiveType", value = "签收方式", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "price", value = "价格", required = true, dataType = "string"),
            })
    @RequestMapping(value = "createOrder", method = RequestMethod.GET)
    @ResponseBody
    public Map register(String receiver, String telephone, String address,
                        String information, String size, String deliveryTime,
                        String classification, String receiveType, String state,
                        String price, Long createTime, String payTime) {
        Map map = new HashMap();

        First first = new First();
        first.setOrderId(CreateOrderID.getCurrentTimeWithoutSpace());
        first.setReceiver(receiver);
        first.setTelephone(telephone);
        first.setAddress(address);
        first.setInformation(information);
        first.setSize(size);
        first.setDeliveryTime(deliveryTime);
        first.setClassification(classification);
        first.setReceiveType(receiveType);
        first.setState("0");
        first.setPrice(price);
        first.setCreateTime(CreateOrderID.getCurrentTime());
        firstService.save(first);
        map.put("msg", "执行成功！");
        return map;
    }
}
