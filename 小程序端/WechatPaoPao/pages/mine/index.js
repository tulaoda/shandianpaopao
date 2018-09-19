//index.js
//获取应用实例
const WxPay = require('../../utils/wxPay.js');
const app = getApp()
const SERVER = require('../../utils/server.js');

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    judgeAdmin: false
  },
  onLoad: function() {
    this.judgeAdmin();
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  developing: function() {
    wx.showToast({
      title: '开发中,敬请期待!',
      icon: 'none',
      duration: 2000
    })
  },
  //判断是否为管理员
  judgeAdmin: function() {
    var that = this;
    //轮播图
    SERVER.getJSON('/courier/judgeAdmin', {
      openId: wx.getStorageSync('openid')
    }, function(res) {
      if (res.data.code == 200) {
        that.setData({
          judgeAdmin: true
        })
      }
    })
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  open: function() {
    wx.makePhoneCall({
      phoneNumber: '18920126411' //仅为示例，并非真实的电话号码
    })
  }
})