// pages/type1/index.js
const SERVER = require('../../utils/server.js');
const WxPay = require('../../utils/wxPay.js');
import WxValidate from '../../utils/WxValidate'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username: '',
    address: '',
    phone: '',
    formId: '',
    arrayPostSize: ['请选择', '小件3元', '中件4元(鞋盒大小)', '大件5元'],
    indexPostSize: 0,
    arrayPostTime: ['请选择', '晚上8:30'],
    indexPostTime: 0,
    arrayPostClassify: ['请选择', '包裹', '箱子', '文件'],
    indexPostClassify: 0,
    arrayPostType: ['请选择', '本人签收', '同学代签'],
    indexPostType: 0,
    // price: [0.01, 4, 5, 10]
    price: ''
  },
  //选择快递大小
  bindPickerChangePostSize: function(e) {
    this.setData({
      indexPostSize: e.detail.value,
    })
  },
  bindPickerChangePostTime: function(e) {
    this.setData({
      indexPostTime: e.detail.value,
    })
  },
  bindPickerChangePostClassify: function(e) {
    this.setData({
      indexPostClassify: e.detail.value,
    })
  },
  bindPickerChangePostType: function(e) {
    this.setData({
      indexPostType: e.detail.value,
    })
  },
  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
    })
  },
  formSubmit: function(e) {
    const params = e.detail.value
    console.log(e.detail.formId);
    this.setData({
      formId: e.detail.formId
    })

    SERVER.getJSON('/user/updateFormId', {
      "openId": wx.getStorageSync('openid'),
      "formId": this.data.formId
    }, function(res) {
      console.log('formId发送')
    });
    console.log(params)

    // 传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      this.showModal(error)
      return false
    }

    let postSize = this.data.arrayPostSize[this.data.indexPostSize];
    let postTime = this.data.arrayPostTime[this.data.indexPostTime];
    let postClassify = this.data.arrayPostClassify[this.data.indexPostClassify];
    let postType = this.data.arrayPostType[this.data.indexPostType];
    console.log(postSize + postTime + postClassify + postType)
    if (postSize | postTime | postClassify | postType == '请选择') {
      this.showModal({
        msg: '请选择快递大小及其他信息',
      })
      return;
    }
    // var that = this;
    // var formData = new FormData();
    let {
      address,
      classification,
      deliveryTime,
      information,
      receiveType,
      receiver,
      size,
      telephone,
      price
    } = e.detail.value;
    wx.showLoading({
      title: 'loading',
    })

    setTimeout(function() {
      wx.hideLoading()
    }, 1000)
    SERVER.postJSON('/first/createOrder', {
      "address": address,
      "classification": classification,
      "deliveryTime": deliveryTime,
      "information": information,
      "price": price,
      "receiveType": receiveType,
      "receiver": receiver,
      "size": size,
      "telephone": telephone,
      "openId": wx.getStorageSync('openid')
    }, function(res) {
      WxPay.wxPay(
        res.data.orderId,
        res.data.fee
      );

      console.log(res.data)
      console.log("======");
    });
  },
  initValidate() {
    // 验证字段的规则
    const rules = {
      receiver: {
        required: true,
      },
      telephone: {
        required: true,
        tel: true,
      },
      address: {
        required: true,
      },
      information: {
        required: true,
      },

    }

    // 验证字段的提示信息，若不传则调用默认的信息
    const messages = {
      receiver: {
        required: '请输入收货人',
      },
      telephone: {
        required: '请输入手机号',
        tel: '请输入正确的手机号',
      },
      address: {
        required: '请输入地址',
      },
      information: {
        required: '请输入快递信息',
      },


    }

    // 创建实例对象
    this.WxValidate = new WxValidate(rules, messages)

    // 自定义验证规则
    this.WxValidate.addMethod('assistance', (value, param) => {
      return this.WxValidate.optional(value) || (value.length >= 1 && value.length <= 2)
    }, '请勾选1-2个敲码助手')
  },


  //获取用户信息
  getUser: function() {
    var that = this;
    SERVER.getJSON('/user/findUser', {
      openId: wx.getStorageSync('openid')
    }, function(res) {
      that.setData({
        phone: res.data.tel,
        address: res.data.address,
        username: res.data.name
      })
    });
    // console.log(username + address + phone + school + openid)

  },
  //查询价格
  findAllPrice: function() {
    var that = this;
    SERVER.getJSON('/price/findAllPrice', {}, function(res) {
      that.setData({
        price: res.data.content
      })
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.findAllPrice();
    this.initValidate();
    this.getUser();
    console.log(this.WxValidate)
  },
  formReset: function() {
    console.log('form发生了reset事件')
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {
    this.formReset()
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }

})