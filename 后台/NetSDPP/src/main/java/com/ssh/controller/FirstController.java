package com.ssh.controller;

import com.ssh.entity.First;
import com.ssh.entity.User;
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
        first = firstService.findFirstByOrderId(orderId);
        first.setState(state);
        if (state.equals('1')) {
            first.setPayTime(CreateOrderID.getCurrentTime());
        }
        //订单状态为:已接单
//        if (state.equals('2')) {
        //接单时间
        first.setCourierId(courierId);
        first.setReceiptTime(CreateOrderID.getCurrentTime());
        //发送模板消息
        // sendTemplateMsg(openId, orderId, courierId);
//        }
        map.put("msg", sendTemplateMsg(openId, orderId, courierId, form_id));
        firstService.saveOrUpdate(first);
        map.put("msg", ResultStatus.SUCCESS.getCode());
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

    //发送模板消息
    public int sendTemplateMsg(String openid, Long orderId, String courierId, String form_id) {
        //获取token
        String token = getAccessToken();
        //获取模板
        Template template = getTemplate(openid, orderId, courierId, form_id);
        //发送请求
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", token);
        String jsonResult = HttpRequest.sendPost(requestUrl, template.toJSON());
        JSONObject json = JSONObject.fromObject(jsonResult);
        return (int) json.getInt("errcode");
    }

    //获取access_token
    public String getAccessToken() {
        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = Constant.APP_ID;
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = Constant.APP_SECRET;
        //授权（必填）
        String grant_type = "client_credential";
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&grant_type=" + grant_type;
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.fromObject(sr);
        //获取token
        String access_token = (String) json.get("access_token");
        return access_token;
    }

    //模板
    public Template getTemplate(String openid, Long orderId, String courierId, String form_id) {
        Template tem = new Template();
        //模板ID
        tem.setTemplateId("LpLUZfwaHsh2bFTqXty0zROG-GbEndBBjTOp2zTyIAw");
        tem.setTopColor("#00DD00");
        //用户openID
        tem.setForm_id(form_id);
        tem.setToUser(openid);
        tem.setUrl("");
        //订单信息
        String orderIdStr = orderId + "";

        First first = firstService.findFirstByOrderId(orderId);
        User user = userService.getUserByOpenId(courierId);


        List<TemplateParam> paras = new ArrayList<TemplateParam>();
        paras.add(new TemplateParam("订单号", orderIdStr, "#FF3333"));
        paras.add(new TemplateParam("商品名称", "闪电跑跑服务", "#0044BB"));
        paras.add(new TemplateParam("订单状态", first.getState(), "#0044BB"));
        paras.add(new TemplateParam("接单人", user.getName(), "#0044BB"));
        paras.add(new TemplateParam("联系电话", user.getTelephone(), "#0044BB"));
        paras.add(new TemplateParam("订单金额", first.getPrice().toString(), "#0044BB"));
        paras.add(new TemplateParam("接单时间", first.getReceiptTime(), "#0044BB"));
        tem.setTemplateParamList(paras);
        return tem;
    }

}
