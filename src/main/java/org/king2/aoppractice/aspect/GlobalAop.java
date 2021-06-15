package org.king2.aoppractice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.king2.aoppractice.annotation.Permissions;
import org.king2.aoppractice.config.SystemFilterConfiguration;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:全局请求面向切面操作
 * @author 刘梓江
 * @Date 2021/3/22 16:06
 */
@Aspect
@Component
public class GlobalAop {

    @Around("execution( * org.king2.aoppractice.controller.*.* (..))")
    public Object globalRequest(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = SystemFilterConfiguration.contentRequest.get();
        System.out.println("接口名称："+request.getServletPath());

        //接收请求参数
        StringBuilder requestParameter=new StringBuilder();
        Enumeration<String> paraNames=request.getParameterNames();
        for(Enumeration<String> e=paraNames;e.hasMoreElements();){
            String parameterName=e.nextElement();
            String parameterValue=request.getParameter(parameterName);
            requestParameter.append(parameterName + "=" + parameterValue + "&");
        }
        System.out.println("请求接口参数"+requestParameter);


        //接收该方法接口参数及修改接口参数信息
        Object[] paramValues = pjp.getArgs();
        String[] paramNames = ((CodeSignature) pjp.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            if(paramNames[i].equals("userName")){
                paramValues[i]="二次修改值";
            }
        }


        //获取当前接口上是否有指定注解
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        Method realMethod = pjp.getTarget().getClass().getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes());
        Permissions annotation = realMethod.getAnnotation(Permissions.class);
        if(annotation!=null){
            System.out.println(Arrays.asList(annotation.targetModule()).toString());
            System.out.println("该接口上有注解");
        }else{
            System.out.println("该接口上没有注解");
        }

        //获取当前目录resources目录下文件信息方式1
        File file1 = ResourceUtils.getFile("classpath:templates/my1.txt");
        InputStream inputStream1=new FileInputStream(file1);
        BufferedReader reader1=new BufferedReader(new InputStreamReader(inputStream1));
        System.out.println(reader1.readLine());
        reader1.close();

        //获取当前目录resources目录下文件信息方式2
        File file2 = new File(new File(ResourceUtils.getURL("classpath:").getPath()).getAbsolutePath(), "templates/my2.txt");
        InputStream inputStream2=new FileInputStream(file2);
        BufferedReader reader2=new BufferedReader(new InputStreamReader(inputStream2));
        System.out.println(reader2.readLine());
        reader2.close();

        //获取当前目录resources目录下文件信息方式3
        ClassPathResource resource = new ClassPathResource("templates/my3.txt");
        InputStream inputStream3 = resource.getInputStream();
        BufferedReader reader3=new BufferedReader(new InputStreamReader(inputStream3));
        System.out.println(reader3.readLine());
        reader3.close();

        //获取当前目录resources目录下文件信息方式4
        File file4 =new File(new ApplicationHome(getClass()).getSource().getParentFile().getPath()+"/classes/templates/my4.txt");
        InputStream inputStream4=new FileInputStream(file4);
        BufferedReader reader4=new BufferedReader(new InputStreamReader(inputStream4));
        System.out.println(reader4.readLine());
        reader4.close();

        //获取当前目录resources目录下文件信息方式5
        InputStream templateStream5 = this.getClass().getClassLoader().getResourceAsStream("templates/my5.txt");
        BufferedReader reader5=new BufferedReader(new InputStreamReader(templateStream5));
        System.out.println(reader5.readLine());
        reader5.close();

        Object proceed = pjp.proceed(paramValues);

        return proceed;
    }
}
