package com.online.edu.controller;


import com.online.edu.common.R;
import org.springframework.web.bind.annotation.*;


/**
 * @author xujin
 * @createtime 2020-04-14 18:24
 * @description
 */
@RestController
@RequestMapping("/edu/user")
//解决跨域问题，如果http,地址和端口号不一样就是跨域
@CrossOrigin
public class LoginController {
    @PostMapping("/login")
    //{"code":20000,"data":{"token":"admin"}}
    //{"code":20000,"data":{"token":"admin"}}
    public R login(){
        return R.ok().data("token","admin");
    }
    @GetMapping("/info")
    //{"code":20000,"data":{"roles":["admin"],"name":"admin","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
    //{"code":20000,"data":{"roles":"[admin]","name":"admin","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
