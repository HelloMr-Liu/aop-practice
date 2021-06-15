package org.king2.aoppractice.config;


import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:系统访问过滤器配置
 * @author 刘梓江
 * @Date 2021/3/23 15:45
 */
@Configuration
public class SystemFilterConfiguration implements Filter {


    //存储本次请求线程请求信息
    public static final ThreadLocal<HttpServletRequest> contentRequest = new ThreadLocal<>();
    //存储本次请求线程响应信息
    public static final ThreadLocal<HttpServletResponse> contentResponse = new ThreadLocal<>();

    @Override
    public void init(FilterConfig filterConfig)  {
    }

    @Override
    public void doFilter(ServletRequest r1, ServletResponse r2, FilterChain filterChain) throws IOException, ServletException {
        MyHttpServletRequestConfiguration request=new MyHttpServletRequestConfiguration((HttpServletRequest)r1);
        HttpServletResponse response=(HttpServletResponse)r2;
        contentRequest.set(request);
        contentResponse.set(response);

        //指定已参数信息
        Map<String,String[]> parameterMap=new HashMap<>(request.getParameterMap());
        parameterMap.put("userName",new String[]{"157170074901"});
        parameterMap.put("encryptionParameter",new String[]{"5555555555555"});
        parameterMap.put("userPassword",new String[]{"liuzijiang1314"});
        parameterMap.remove("desc");
        request.setParameterMap(parameterMap);

        //指定请求对象和响应对并放行接口
        filterChain.doFilter(request,response);

        contentRequest.remove();
        contentResponse.remove();
    }

    @Override
    public void destroy() {
    }
}

