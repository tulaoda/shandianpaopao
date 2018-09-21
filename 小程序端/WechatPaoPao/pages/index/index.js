const SERVER = require('../../utils/server.js');
Page({
  data: {
    imgUrls: [],
    indicatorDots: true,
    autoplay: true,
    interval: 5000,
    duration: 1000,
    grids: [0, 1, 2, 3]
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(e) {
    var that = this;
    //轮播图
    SERVER.getJSON('/banner/listBanner', {}, function(res) {
      let imgUrls = [];
      res.data.content.map(item => {
        imgUrls.push(item.imgUrl)
      });
      that.setData({
        imgUrls: imgUrls,
      })
    });

  },
  banner_detail: function() {
    wx.navigateTo({
      url: '../banner_detail/index',
    });
  },
  developing: function() {
    wx.showToast({
      title: '开发中,敬请期待!',
      icon: 'none',
      duration: 2000
    })
  },
  changeIndicatorDots: function(e) {
    this.setData({
      indicatorDots: !this.data.indicatorDots
    })
  },
  changeAutoplay: function(e) {
    this.setData({
      autoplay: !this.data.autoplay
    })
  },
  intervalChange: function(e) {
    this.setData({
      interval: e.detail.value
    })
  },
  durationChange: function(e) {
    this.setData({
      duration: e.detail.value
    })
  }
})