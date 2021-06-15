package org.king2.aoppractice.controller;

import org.king2.aoppractice.annotation.Permissions;
import org.king2.aoppractice.entity.vo.SystemResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述:测试请求接口控制器
 * @author 刘梓江
 * @Date 2021/6/15 14:32
 */
@RestController
public class TestController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/test1")
    public Object test1(String encryptionParameter){
        return SystemResultVo.ok("test1");
    }

    @Permissions(targetModule = {"per1","per2"})
    @RequestMapping("/test2")
    public Object test2(String encryptionParameter,String userName){
        String userName1 = request.getParameter("userName");
        System.out.println(userName1);
        return SystemResultVo.ok(userName);
    }
}
