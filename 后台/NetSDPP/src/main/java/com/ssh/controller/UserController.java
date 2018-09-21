package com.ssh.controller;

import java.util.HashMap;
import java.util.Map;

import com.ssh.entity.Banner;
import com.ssh.entity.User;
import com.ssh.service.UserService;
//import com.ssh.utils.AesCbcUtil;
import com.ssh.utils.HttpRequest;
import com.ssh.wxpay.constant.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Api(value = "user")
@Controller
@RequestMapping(value = "/user") // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {
    @Autowired
    private UserService userService;

    @ApiImplicitParams(
            {@ApiImplicitParam(name = "code", value = "小程序登录时获取的code", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "encryptedData", value = "密文 encryptedData", required = true, dataType = "string"),
                    @ApiImplicitParam(name = "iv", value = "偏移向量 iv", required = true, dataType = "string")
            })
    @ResponseBody
    @RequestMapping(value = "decodeUserInfo", method = RequestMethod.GET)
    public Map decodeUserInfo(String encryptedData, String iv, String code) {

        Map map = new HashMap();
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }

        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = Constant.APP_ID;
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = Constant.APP_SECRET;
        //授权（必填）
        String grant_type = "authorization_code";


        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.fromObject(sr);
        //获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");

        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
//        try {
//            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
//            if (null != result && result.length() > 0) {
//                map.put("status", 1);
//                map.put("msg", "解密成功");
//
//                JSONObject userInfoJSON = JSONObject.fromObject(result);
//                Map userInfo = new HashMap();
//                userInfo.put("openId", userInfoJSON.get("openId"));
//                userInfo.put("nickName", userInfoJSON.get("nickName"));
//                userInfo.put("gender", userInfoJSON.get("gender"));
//                userInfo.put("city", userInfoJSON.get("city"));
//                userInfo.put("province", userInfoJSON.get("province"));
//                userInfo.put("country", userInfoJSON.get("country"));
//                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
//                userInfo.put("unionId", userInfoJSON.get("unionId"));
//                map.put("userInfo", userInfo);
//                return map;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        map.put("status", 1);
        map.put("msg", "解密成功");
        map.put("openid", openid);
        return map;
    }


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


    @RequestMapping(value = "updateFormId", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> findUser(String openId, String formId) {
        Map<String, String> map = new HashMap<String, String>();
        User user = userService.getUserByOpenId(openId);
        user.setFormId(formId);
        userService.saveOrUpdate(user);
        if (user == null) {
            map.put("msg", "执行失败！");
            return map;
        }
        map.put("msg", "200");
        return map;
    }

    @RequestMapping(value = "findUser", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> findUser(String openId) {
        Map<String, String> map = new HashMap<String, String>();
        Banner banner = new Banner();
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
