//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    name:"",
    type:"",
    openid:"",
    Height: 0,
    scale: 16,
    delet :"0",
    iconPath2:"",
    latitude: "1",// 经纬度
    longitude: "112",
    markers: [],
    controls: [{
     id: 1,
     iconPath: '/pages/assests/imgs/jian.png',
     position: {
     left: 280,
     top: 100 - 50,
     width: 30,
     height: 30
     },
     clickable: true
    },
    {
     id: 2,
     iconPath: '/pages/assests/imgs/jia.png',
     position: {
     left: 320,
     top: 100 - 50,
     width: 30,
     height: 30
     },
     clickable: true
    }
    ],
    circles: []
   
  },

  onLoad: function () {
    var _this = this;
    wx.getSystemInfo({
      success: function (res) {
      //设置map高度，根据当前设备宽高满屏显示
        _this.setData({
            view: {
            Height: res.windowHeight
            }
        })
      }
    }),

    console.log(2222), 
    console.log("获取位置"), 
    wx.getLocation({
      
      type: 'wgs84', // 默认为 wgs84 返回 gps 坐标，gcj02 返回可用于 wx.openLocation 的坐标
      
      success: function (res) {
      _this.setData({
      
       latitude: res.latitude,
       longitude: res.longitude,
       markers: [{
       id: "0",
       latitude: res.latitude,
       longitude: res.longitude,
       iconPath:'/pages/assests/imgs/my.png',
       width: 30,
       height: 30,
       title: "当前位置"
       }]    ,

      //  circles: [{
      //  latitude: res.latitude,
      //  longitude: res.longitude,
      //  color: '#FF0000DD',
      //  fillColor: '#7cb5ec88',
      //  radius: 3000,
      //  strokeWidth: 1
      //  }]
    
      })
      }
    
     })
     var times = 0
     var i = setInterval(function() {
      times++
      
      if (times >= 600) {
      

           clearInterval(i)
      } else {
       wx.request({
         url: 'https://jzstxdy.cn:9999/mytest2/Servlet', //本地服务器地址
         data: { //data中的参数值就是传递给后台的数据
          latitude: _this.data.latitude,
          longitude:_this.data.longitude,
          type:_this.data.type,
          name:_this.data.name,
          delet: _this.data.delet
         },
         method: 'get',
         header: {
           'content-type': 'application/json' //默认值
         },
         success: function(res) { //res就是接收后台返回的数据
          _this.setData({
             tt: res.data

           })
           console.log("delt的值"+_this.data.delet);
           for (var i = 1; i < res.data.length+1; i++) {
            // res.data; 
          //   console.log(res.data[i]); 
            var param6 = [];

            let mark = "markers[" + i + "]";
            let id = "markers[" + i + "].id";
            let iconPath = "markers[" + i + "].iconPath";
            let latitude = "markers[" + i + "].iconPath";
            let longitude = "markers[" + i + "].longitude";
            let width = "markers[" + i + "].width";
            let height = "markers[" + i + "].height";
        
          
            if (res.data[i-1].type ==1){
              _this.data.iconPath2 = '/pages/assests/imgs/siji.png'
            }
            else
            _this.data.iconPath2 = '/pages/assests/imgs/yonghu.png'
            _this.setData({[mark]:{ id: res.data[i-1].id, iconPath: _this.data.iconPath2, latitude: res.data[i-1].latitude, longitude: res.data[i-1].longitude, width: 30, height: 30 }});
          }

           console.log(res.data);
           
         },
         fail: function(res) {
           console.log(res);
           console.log("失败");
         }
       })
       if(_this.data.delet=="1")
        {
          _this.data.name="";
        }       
       _this.data.delet="0"
      
      }
 }, 3000)
  },
    //点击merkers
    markertap(e) {
      var that = this;
      
        wx.showActionSheet({
          itemList: ["手动修改位置","取消叫车","叫车"],
          success: function (res) {
       console.log("地图点击");
       if (res.tapIndex==0){
         wx.chooseLocation({
           success: function(res) {
             console.log("地图点击事件：" + JSON.stringify(res));
             var user_longitude = res.longitude;
             var user_lagitude = res.latitude;
             var user_address = res.address;
             var nowAddress = {};
             nowAddress["name"] = res.name;
             nowAddress["desc"] = res.address;
             that.setData({
               latitude: user_lagitude,
               longitude: user_longitude,
               addressName: user_address,
               textData: nowAddress,
               markers:[{}],
               markers: [{
                 id: "0",
                 iconPath:'/pages/assests/imgs/my.png',
                 latitude: user_lagitude,
                 longitude: user_longitude,
                 width: 30,
                 height: 30,
                 title: "自己"
                 }],
             });
             //移动marker
           },
           fail: function(res) {  
             console.log("点击地图fail:" + JSON.stringify(res));     
           },
                complete: function(res) {        // complete
             console.log("点击地图complete:" + JSON.stringify(res));         
           }
         })
            console.log(res.tapIndex)
             
       }
       else if(res.tapIndex==2){
         that.data.name=wx.getStorageSync('userInfo');
         that.data.type=wx.getStorageSync('type');
         that.data.openid=wx.getStorageSync('openid');
         console.log(that.data.name+ that.data.type+that.data.openid)
       }
       else if(res.tapIndex==1){
        console.log("删除页面")
        that.data.delet="1";
        
      }
   
          },
          fail: function (res) {
          console.log(res.errMsg)
          }
         })

      
    
      },
  
      //点击缩放按钮动态请求数据
  controltap(e) {
    var that = this;
    console.log("scale===" + this.data.scale)
    if (e.controlId === 1) {
     // if (this.data.scale === 13) {
     that.setData({
     scale: --this.data.scale
     })
     // }
    } else {
     // if (this.data.scale !== 13) {
     that.setData({
     scale: ++this.data.scale
     })
     // }
    }
   
   
    },


})
