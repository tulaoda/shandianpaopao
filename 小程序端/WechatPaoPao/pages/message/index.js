//logs.js
var util = require('../../utils/util.js')
Page({
  data: {
    message: []
  },
  onLoad: function () {
    this.setData({
      logs: (wx.getStorageSync('message') || []).map(function (message) {
        return util.formatTime(new Date(message))
      })
    })
  }
})
