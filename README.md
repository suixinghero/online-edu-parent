# online-edu-parent
1.基于微服务架构的在线教育项目

2.在线教育系统，分为前台网站系统和后台运营系统，B2C模式

3.前台用户系统包括首页、课程、讲师、文章和问答这五大部分(目前只做了课程和讲师这两大部分)，后台系统包括讲师管理、课程分类管理、课程管理和统计分析管理这四大部分。
  使用微服务技术架构，前后端分离开发。
  
4.本来应该为把涉及到前台和后台的微服务模块分隔开，但由于资源有限，所有功能相同的都写到一个模块中了。

  online-edu-eureka:微服务注册中心
  
  online-edu-common:基础模块
  
  online-edu-oss:上传图片到阿里云的OSS中的图片模块
  
  online-edu-poi:课程相关的模块
  
  online-edu-teacher:讲师相关的模块
  
  online-edu-user-login:登录相关的模块
  
  online-edu-video:视频相关的模块
  
  online-edu-ucenter:用户相关模块
  
  online-edu-statistics:网站统计日数据相关的模块
  
  online-edu-zuul:网关相关的模块
  
  前台地址：http://49.234.117.202  或  www.suixinghero.cn
  
  后台地址：http://49.234.117.202:9528/
  
5.后端的主要技术架构是：SpringBoot+SpringCloud+MyBatis-Plus+HttpClient+MySQL+Maven+POI+nginx
  
6.前端的架构是：Node.js+Vue.js+element-ui+Nuxt+Echarts

7.其它设计到的中间件包括Redis、ActiceMQ、阿里云OSS、视频点播，后台中使用了ECharts做图表的展现，使用POI完成了课程分类批量添加，分布式使用了
  单点登录使用了JWT，还使用了微信登录。









