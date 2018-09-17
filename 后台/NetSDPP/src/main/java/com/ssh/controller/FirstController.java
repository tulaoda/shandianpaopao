package com.ssh.controller;

import com.ssh.entity.First;
import com.ssh.entity.User;
import com.ssh.service.CourierService;
import com.ssh.service.FirstService;
import com.ssh.service.UserService;
import com.ssh.utils.CreateOrderID;
import com.ssh.utils.HttpRequest;
import com.ssh.utils.enums.ResultStatus;
import com.ssh.wxpay.constant.Constant;
import com.ssh.wxpay.entity.Template;
import com.ssh.wxpay.entity.TemplateParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "first")
@Controller
@RequestMapping(value = "/first")
public class FirstController {
    @Autowired
    private FirstService firstService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourierService courierService;

    @ApiImplicitParams({})
    @RequestMapping(value = "createOrder", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map register(@RequestBody First first) {
        Map map = new HashMap();
        first.setOrderId(CreateOrderID.getCurrentTimeWithoutSpace());
        first.setState("0");
        first.setCreateTime(CreateOrderID.getCurrentTime());
        firstService.save(first);
        map.put("msg", "执行成功！");
        map.put("fee", first.getPrice());
        map.put("orderId", first.getOrderId());
        return map;
    }

//    @ApiImplicitParams({})
//    @RequestMapping(value = "updateStateByOrderId", method = RequestMethod.POST, produces = "application/json")
//    @ResponseBody
//    public Map updateStateByOrderId(Long orderId, String state, String openId) {
//        Map map = new HashMap();
//        Long firstId= null;
//        First first;
//        try {
//            firstId = firstService.findFirstIdByOrderId(orderId);
//            first = firstService.findFirstById(firstId);
//            first.setState(state);
//            firstService.saveOrUpdate(first);
//            map.put("msg", ResultStatus.SUCCESS.getCode());
//            map.put("msg", "更新成功!");
//            //订单状态为:已接单,接单时间,发送模板消息
//            if (state == "2") {
//
//                first.setReceiptTime(CreateOrderID.getCurrentTime());
//
//                map.put("msg", sendTemplateMsg(openId, orderId));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//        return map;
//    }

    @RequestMapping(value = "updateOrderState", method = RequestMethod.GET)
    @ResponseBody
    public Map updateStateByOrderId(
            @RequestParam(required = true, value = "orderId") Long orderId,
            @RequestParam(required = true, value = "state") String state,
            @RequestParam(required = true, value = "openId") String openId,
            @RequestParam(required = false, value = "courierId") String courierId,
            @RequestParam(required = false, value = "form_id") String form_id
    ) throws Exception {
        Map map = new HashMap();
        First first;
        User user;
        first = firstService.findFirstByOrderId(orderId);
        first.setState(state);
        if (state.equals("1")) {
            first.setPayTime(CreateOrderID.getCurrentTime());
        }
        //订单状态为:已接单
        if (state.equals("2")) {
            //接单人,时间
            first.setCourierId(courierId);
            first.setReceiptTime(CreateOrderID.getCurrentTime());
            user = userService.getUserByOpenId(courierId);
            first.setCourierName(user.getName());
            first.setCourierTel(user.getTelephone());
            //发送模板消息
            // sendTemplateMsg(openId, orderId, courierId);
            map.put("templateMsg", firstService.sendTemplateMsg(openId, orderId, courierId, form_id));
        }

        firstService.saveOrUpdate(first);
        map.put("code", ResultStatus.SUCCESS.getCode());
        map.put("msg", "更新成功!");
        return map;
    }

    @ApiImplicitParams({})
    @RequestMapping(value = "orderByState", method = RequestMethod.GET)
    @ResponseBody
    public Map orderByState(String openId, String state, int page, int pageSize) {
        Map map = new HashMap();
        List<First> list = null;
        try {
            list = firstService.orderByState(openId, state, page, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("content", list);
        map.put("msg", "执行成功！");
        return map;
    }


    @ApiImplicitParams({})
    @RequestMapping(value = "orderAllByState", method = RequestMethod.GET)
    @ResponseBody
    public Map orderAllByState(String state, int page, int pageSize) {
        Map map = new HashMap();
        List<First> list = null;
        try {
            list = firstService.orderAllByState(state, page, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("content", list);
        map.put("msg", "执行成功！");
        return map;
    }

    @ApiImplicitParams({})
    @RequestMapping(value = "findCourierByOpenId", method = RequestMethod.GET)
    @ResponseBody
    public Map findCourierByOpenId(String openId) {
        Map map = new HashMap();
        boolean flag = courierService.findCourierByOpenid(openId);
        if (flag) {
            map.put("code", ResultStatus.SUCCESS.getCode());
        } else
            map.put("code", ResultStatus.FAILURE.getCode());
        return map;
    }


}
