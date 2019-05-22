package com.itheima.controller;

import com.itheima.domain.sysLog;
import com.itheima.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class AOPLog {

    private Date visitTime;
    private Method method;
    private Class clazz;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;
    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint js) throws NoSuchMethodException {
       visitTime = new Date();
       clazz = js.getTarget().getClass();
       String methodName = js.getSignature().getName();
        Object[] args = js.getArgs();
        //获取具体执行的方法的Method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName); //只能获取无参数的方法
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method=clazz.getMethod(methodName, classArgs);
        }
    }

    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter(JoinPoint js) {
        Long executionTime = new Date().getTime() - visitTime.getTime();
        String url="";
        if (clazz != null && method != null && clazz != AOPLog.class&&clazz!=SysLogController.class) {
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValues = classAnnotation.value();
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValues = methodAnnotation.value();
                    url=classValues[0]+methodValues[0];
                    String ip= request.getRemoteAddr();
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();

                    String username = user.getUsername();

                    //将日志相关信息封装到SysLog对象
                    sysLog sysLog = new sysLog();
                    sysLog.setExecutionTime(executionTime); //执行时长
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    sysLogService.save(sysLog);
                }
            }
        }


    }
}
