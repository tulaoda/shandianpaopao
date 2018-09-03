const config = require('./utils/config')
App({
  onLaunch: function() {

    //调用API从本地缓存中获取数据
    var message = wx.getStorageSync('message') || []
    message.unshift(Date.now())
    wx.setStorageSync('message', message)


    // 获取用户信息
    wx.getSetting({
        success: res => {
          if (res.authSetting['scope.userInfo']) {
            // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
            wx.getUserInfo({
              success: res => {
                // 可以将 res 发送给后台解码出 unionId
                this.globalData.userInfo = res.userInfo
                // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
                // 所以此处加入 callback 以防止这种情况
                if (this.userInfoReadyCallback) {
                  this.userInfoReadyCallback(res)
                }
              }
            })
          } else {
            wx.showModal({
              title: '注意',
              showCancel: false,
              confirmText: '好去授权',
              content: '为了您更好的体验,请先同意授权',
              success: function(res) {
                wx.navigateTo({
                  url: '../userinfo/index'
                });
              }
            })
          }
        }
      }),
      // 登录
      wx.login({
        success: res => {
          // 发送 res.code 到后台换取 openId, sessionKey, unionId
          if (res.code) { //wx.login获取code。
            //发起网络请求
            wx.request({
              url: config.API_HOST + '/user/decodeUserInfo',
              data: {
                code: res.code //将code发送到后台服务器。
              },
              success: function(res) {
                console.log(res.data.openid);
                wx.setStorageSync('openid', res.data.openid)
              }
            })
          } else {
            console.log('获取用户登录态失败！' + res.errMsg)
          }
        }

      })
  },
  globalData: {
    userInfo: null
  }

})