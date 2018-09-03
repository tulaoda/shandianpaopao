const server = require('../../utils/server');
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
    var that=this;
    //轮播图
    server.getJSON('/banner/listBanner', {}, function(res) {
      let imgUrls = [];
      res.data.content.map(item => {
        imgUrls.push(item.imgUrl)
      });
      that.setData({
        imgUrls:imgUrls,
      })
    });

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