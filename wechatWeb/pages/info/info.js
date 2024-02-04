//获取应用实例
const app = getApp()

Page({
    data:{
      type:"",
      openid:"",
        canIUse: wx.canIUse('button.open-type.getUserInfo'),
        userInfo:""
    },
    

  onLoad: function(options) {

        var that = this;
    wx.login({

      //获取code
      success: function (res) {
        var code = res.code; //返回code
        console.log("这个是code"+code);
        var appId = 'xxxx';
        var secret = 'xxxxx';
        wx.request({
          url: 'https://api.weixin.qq.com/sns/jscode2session?appid=' + appId + '&secret=' + secret + '&js_code=' + code + '&grant_type=authorization_code',
          data: {},
          header: {
            'content-type': 'json'
          },
          success: function (res) {
            var openid = res.data.openid //返回openid
            wx.setStorage({
              key:"openid",
              data:openid
              })
            
            console.log('openid为' + openid);
          },
          fail: function (res) {
            console.log('获取opeid失败')
            }
        })
      }
    })
        //查看是否授权
        
        wx.getSetting({
    
          success: function(res) {
          wx.getUserInfo({
            success: res => {
             
              wx.setStorage({
                key:"userInfo",
                data:res.userInfo.nickName
                })
            }

          })


            if (res.authSetting['scope.userInfo']) {
      wx.redirectTo({
        url: '../index/index'
      })

              console.log("用户授权了");
      
            } else {
    
              //用户没有授权
    
              console.log("用户没有授权");
    
            }
    
          }
    
        });
    //根据code获取openid等信息


},
    
    bindGetDriverInfo: function(res) {
        if (res.detail.userInfo) {
    
          //用户按了允许授权按钮
    
          var that = this;
    
          // 获取到用户的信息了，打印到控制台上看下
    
          console.log("用户的信息如下：");
          
          console.log(res.detail.userInfo.nickName);

            wx.setStorage({
            key:"userInfo",
            data:res.detail.userInfo
            })
            this.data.type =1;
            wx.setStorage({
              key:"type",
              data:that.data.type
              })
          //授权成功后,通过改变 isHide 的值，让实现页面显示出来，把授权页面隐藏起来
          wx.redirectTo({
            url: '../index/index'
          })
          that.setData({
            
            isHide: false
    
          });
    
        } else {
    
           that.setData({
                        isHide: true
                    });
    
        }
     },
    bindGetUserInfo: function(res) {
          if (res.detail.userInfo) {
      
            //用户按了允许授权按钮
      
            var that = this;
      
            // 获取到用户的信息了，打印到控制台上看下
      
            console.log("用户的信息如下：");
      
            console.log(res.detail.userInfo.nickName);
            wx.setStorage({
              key:"userInfo",
              data:res.detail.userInfo
              })
              this.data.type =2;
              wx.setStorage({
                key:"type",
                data:that.data.type
                })
            //授权成功后,通过改变 isHide 的值，让实现页面显示出来，把授权页面隐藏起来
            wx.redirectTo({
              url: '../index/index'
            })
            that.setData({
              
              isHide: false
      
            });
      
          } else {
      
             that.setData({
                          isHide: true
                      });
      
          }
    }
})
