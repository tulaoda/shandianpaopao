package com.ssh.service.impl;

import com.ssh.dao.FirstDao;
import com.ssh.dao.UserDao;
import com.ssh.entity.First;
import com.ssh.entity.User;
import com.ssh.service.FirstService;
import com.ssh.utils.HttpRequest;
import com.ssh.wxpay.constant.Constant;
import com.ssh.wxpay.entity.Template;
import com.ssh.wxpay.entity.TemplateParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FirstServiceImpl implements FirstService {
    @Autowired
    private FirstDao firstDao;
    @Autowired
    private UserDao userDao;

    public First load(Long id) {
        return null;
    }

    public First get(Long id) {
        return null;
    }

    public List<First> findAll() {
        return null;
    }

    public void persist(First entity) {

    }

    public Long save(First entity) {
        return firstDao.save(entity);
    }

    public void saveOrUpdate(First entity) {
        firstDao.saveOrUpdate(entity);
    }

    public void delete(Long id) {

    }

    public void flush() {

    }

    public void updateFirstStateByOrderId(Long orderId, String state) {
        firstDao.updateFirstStateByOrderId(orderId, state);
    }

    public List<First> orderByState(String openId, String state, int page, int pageSize) {
        return firstDao.orderByState(openId, state, page, pageSize);
    }

    public List<First> orderAllByState(String state, int page, int pageSize) {
        return firstDao.orderAllByState(state, page, pageSize);
    }

    public Long findFirstIdByOrderId(Long orderId) {
        return firstDao.findFirstIdByOrderId(orderId);
    }

    public First findFirstByOrderId(Long orderId) {
        return firstDao.findFirstByOrderId(orderId);
    }

    public First findFirstById(Long id) {
        return firstDao.findFirstById(id);
    }

    public Double getPrice(Long orderId) {
        return firstDao.getPrice(orderId);
    }


    //发送模板消息
    public int sendTemplateMsg(String openid, Long orderId, String courierId, String form_id) {
        //获取token
        String token = getAccessToken();
        //获取模板
        Template template = getTemplate(openid, orderId, courierId, form_id);
        //发送请求  https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN
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
        form_id = userDao.getUserByOpenId(openid).getFormId();
        tem.setForm_id(form_id);
        tem.setToUser(openid);
        tem.setUrl("");
        tem.setPage("/pages/order1/index");
        //订单信息
        String orderIdStr = orderId + "";

        First first = firstDao.findFirstByOrderId(orderId);
        User user = userDao.getUserByOpenId(courierId);


        List<TemplateParam> paras = new ArrayList<TemplateParam>();
        paras.add(new TemplateParam("keyword1", orderIdStr, "#FF3333"));
        paras.add(new TemplateParam("keyword2", "闪电跑跑服务", "#0044BB"));
        paras.add(new TemplateParam("keyword3", "已接单", "#0044BB"));
        paras.add(new TemplateParam("keyword4", user.getName(), "#0044BB"));
        paras.add(new TemplateParam("keyword5", user.getTelephone(), "#0044BB"));
        paras.add(new TemplateParam("keyword6", first.getPrice().toString(), "#0044BB"));
        paras.add(new TemplateParam("keyword7", first.getReceiptTime(), "#0044BB"));
        tem.setData(paras);
        return tem;
    }
}
