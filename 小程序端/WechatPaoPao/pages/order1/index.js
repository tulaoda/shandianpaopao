//index.js
//获取应用实例
// var apiUtils = require('../../utils/apiUtils')
// var types = require('../../utils/constant').MAIN_TYPE
const SERVER = require('../../utils/server.js');
var app = getApp()
Page({
  data: {
    tabs: ["待付款", "待接单", "配送中", "已完成"],
    activeIndex: "0",
    sliderOffset: 0,
    sliderLeft: 0,
    zhanxun: {
      page: 1,
      limit: 5
    },
    noPayData: [],

  },
  onLoad: function() {
    var that = this;
    wx.getSystemInfo({
      success: function(res) {
        that.setData({
          sliderLeft: (res.windowWidth / that.data.tabs.length - res.sliderWidth) / 2
        });
      }
    });
    this.request_book_content_data()
  },
  onShow: function() {
    wx.setNavigationBarTitle({
      title: '订单列表',
      success: function(res) {
        console.log(res)
      }
    })
  },
  tabClick: function(e) {
    this.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id
    });
    if (e.currentTarget.id == 1) {
      this.request_book_content_data()
      console.log('content')
    }
    // if (e.currentTarget.id == 2 && this.data.contentData.length == 0) {
    //   this.request_book_content_data()
    //   console.log('content')
    // }
    if (e.currentTarget.id == 2) {
      this.request_book_content_data()
      console.log('content')
    }
    if (e.currentTarget.id == 3) {
      this.request_book_content_data()
      console.log('book')
    }

  },
  //以下为scrollview
  lower: function(e) {
    console.log('滚动到底部了')
    console.log(e)
    var zhanxun = this.data.zhanxun;
    zhanxun.page = zhanxun.page + 1;
    this.setData({
      zhanxun: zhanxun,
    })
    this.request_discover_data()
  },
  exhibition_lower: function(e) {
    console.log('滚动到底部了')
    console.log(e)
    var exhibition = this.data.exhibition;
    exhibition.page = exhibition.page + 1;
    this.setData({
      exhibition: exhibition,
    })
    this.request_exhibition_data()
  },
  request_book_content_data: function(type) {
    wx.showToast({
      title: '加载中',
      icon: 'loading',
      duration: 1000
    })
    wx.showNavigationBarLoading()
    var that = this;
    SERVER.getJSON('/first/orderByState', {
      "openId": "o8WqA4mgJdUCX3iGPMDanfKZDDE8",
      "state": 0
    }, function(res) {
      console.log(res)
      wx.hideToast()
      wx.hideNavigationBarLoading()
      that.setData({
        noPayData: that.data.noPayData.concat(res.data.content)
      })
    })


  },
  //以上为scrollview
  error: function(e) {
    console.log(e)
  }
})