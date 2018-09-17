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
    form_id: '',
    state_0: {
      page: 1,
      pageSize: 5
    },
    state_1: {
      page: 1,
      pageSize: 5
    },
    state_2: {
      page: 1,
      pageSize: 5
    },
    state_3: {
      page: 1,
      pageSize: 5
    },
    state_0_Data: [],
    state_1_Data: [],
    state_2_Data: [],
    state_3_Data: [],
  },
  onLoad: function() {
    var that = this;
    this.setData({
      state_0: {
        page: 1,
        pageSize: 5
      },
      state_1: {
        page: 1,
        pageSize: 5
      },
      state_2: {
        page: 1,
        pageSize: 5
      },
      state_3: {
        page: 1,
        pageSize: 5
      },
      state_0_Data: [],
      state_1_Data: [],
      state_2_Data: [],
      state_3_Data: [],
    })
    wx.getSystemInfo({
      success: function(res) {
        that.setData({
          sliderLeft: (res.windowWidth / that.data.tabs.length - res.sliderWidth) / 2
        });
      }
    });
    this.request_order_state_0_data();
    this.request_order_state_1_data();
    this.request_order_state_2_data();
    this.request_order_state_3_data();
  },
  onShow: function() {
    wx.setNavigationBarTitle({
      title: '订单列表',
      success: function(res) {
        console.log(res)
      }
    })
  },
  contactReciver: function(e) {
    wx.makePhoneCall({
      phoneNumber: e.currentTarget.dataset.contacttel //仅为示例，并非真实的电话号码
    })
  },
  tabClick: function(e) {
    this.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id
    });
    if (e.currentTarget.id == 0 && this.data.state_0_Data.length == 0) {
      this.request_order_state_0_data()
      console.log('content')
    }
    if (e.currentTarget.id == 1 && this.data.state_1_Data.length == 0) {
      this.request_order_state_1_data()
      console.log('content')
    }
    // if (e.currentTarget.id == 2 && this.data.contentData.length == 0) {
    //   this.request_book_content_data()
    //   console.log('content')
    // }
    if (e.currentTarget.id == 2 && this.data.state_2_Data.length == 0) {
      this.request_order_state_2_data()
      console.log('content')
    }
    if (e.currentTarget.id == 3 && this.data.state_3_Data.length == 0) {
      this.request_order_state_3_data()
      console.log('book')
    }

  },


  submitInfo: function(e) {
    console.log(e.detail.formId);
    this.setData({
      form_id: e.detail.formId
    })
    console.log(e.currentTarget.dataset.openid)
    SERVER.getJSON('/first/updateOrderState', {
      orderId: e.currentTarget.id,
      state: 2,
      courierId: wx.getStorageSync('openid'),
      openId: e.currentTarget.dataset.openid,
      form_id: e.detail.formId
    }, function(res) {
      if (res.data.msg = '更新成功') {
        if (getCurrentPages().length != 0) {
          //刷新当前页面的数据
          getCurrentPages()[getCurrentPages().length - 1].onLoad()
        }
      }
      console.log(res)
    })

  },
  finish: function(e) {
    console.log(e.currentTarget.id)
    SERVER.getJSON('/first/updateOrderState', {
      orderId: e.currentTarget.id,
      state: 3,
      openId: wx.getStorageSync('openid')
    }, function(res) {
      if (res.data.msg = '更新成功') {
        if (getCurrentPages().length != 0) {
          //刷新当前页面的数据
          getCurrentPages()[getCurrentPages().length - 1].onLoad()
        }
      }
      console.log(res)
    })
  },
  //以下为scrollview
  // lower: function(e) {
  //   console.log('滚动到底部了')
  //   console.log(e)
  //   var zhanxun = this.data.zhanxun;
  //   zhanxun.page = zhanxun.page + 1;
  //   this.setData({
  //     zhanxun: zhanxun,
  //   })
  //   this.request_discover_data()
  // },
  exhibition_lower: function(e) {
    if (this.data.activeIndex == 0) {
      var state_0 = this.data.state_0;
      state_0.page = state_0.page + 1;
      this.setData({
        state_0: state_0,
      })
      this.request_order_state_0_data()
      console.log('content')
    }
    if (this.data.activeIndex == 1) {
      var state_1 = this.data.state_1;
      state_1.page = state_1.page + 1;
      this.setData({
        state_1: state_1,
      })
      this.request_order_state_1_data()
      console.log('content')
    }
    if (this.data.activeIndex == 2) {
      var state_2 = this.data.state_2;
      state_2.page = state_2.page + 1;
      this.setData({
        state_2: state_2,
      })
      this.request_order_state_2_data()
      console.log('content')
    }
    if (this.data.activeIndex == 3) {
      var state_3 = this.data.state_3;
      state_3.page = state_3.page + 1;
      this.setData({
        state_3: state_3,
      })
      this.request_order_state_3_data()
      console.log('book')
    }
    // console.log('滚动到底部了')
    // console.log(e)
    // var state_0 = this.data.state_0;
    // state_0.page = state_0.page + 1;
    // this.setData({
    //   state_0: state_0,
    // })
    // this.request_order_state_0_data();
  },

  // state_1_lower: function(e) {
  //   console.log('滚动到底部了!!')
  //   console.log(e)
  //   var state_0 = this.data.state_0;
  //   state_0.page = state_0.page + 1;
  //   this.setData({
  //     state_0: state_0,
  //   })
  //   this.request_order_state_0_data();
  // },


  request_order_state_0_data: function(type) {
    wx.showToast({
      title: '加载中',
      icon: 'loading',
      duration: 1000
    })
    wx.showNavigationBarLoading()
    var that = this;
    SERVER.getJSON('/first/orderAllByState', {
      "state": 0,
      "page": this.data.state_0.page,
      "pageSize": this.data.state_0.pageSize
    }, function(res) {
      console.log(res)
      wx.hideToast()
      wx.hideNavigationBarLoading()
      that.setData({
        state_0_Data: that.data.state_0_Data.concat(res.data.content)
      })
    })
  },

  request_order_state_1_data: function(type) {
    wx.showToast({
      title: '加载中',
      icon: 'loading',
      duration: 1000
    })
    wx.showNavigationBarLoading()
    var that = this;
    SERVER.getJSON('/first/orderAllByState', {
      "state": 1,
      "page": this.data.state_1.page,
      "pageSize": this.data.state_1.pageSize
    }, function(res) {
      console.log(res)
      wx.hideToast()
      wx.hideNavigationBarLoading()
      that.setData({
        state_1_Data: that.data.state_1_Data.concat(res.data.content)
      })
    })
  },

  request_order_state_2_data: function(type) {
    wx.showToast({
      title: '加载中',
      icon: 'loading',
      duration: 1000
    })
    wx.showNavigationBarLoading()
    var that = this;
    SERVER.getJSON('/first/orderAllByState', {
      "state": 2,
      "page": this.data.state_2.page,
      "pageSize": this.data.state_2.pageSize
    }, function(res) {
      console.log(res)
      wx.hideToast()
      wx.hideNavigationBarLoading()
      that.setData({
        state_2_Data: that.data.state_2_Data.concat(res.data.content)
      })
    })
  },

  request_order_state_3_data: function(type) {
    wx.showToast({
      title: '加载中',
      icon: 'loading',
      duration: 1000
    })
    wx.showNavigationBarLoading()
    var that = this;
    SERVER.getJSON('/first/orderAllByState', {
      "state": 3,
      "page": this.data.state_3.page,
      "pageSize": this.data.state_3.pageSize
    }, function(res) {
      console.log(res)
      wx.hideToast()
      wx.hideNavigationBarLoading()
      that.setData({
        state_3_Data: that.data.state_3_Data.concat(res.data.content)
      })
    })
  },
  //以上为scrollview
  error: function(e) {
    console.log(e)
  }
})