package com.neusoft.hr.sys.interceptor;

import com.neusoft.hr.business.entity.Menu;
import com.neusoft.hr.business.entity.User;
import com.neusoft.hr.sys.annotation.Permission;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截权限
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Method method1 = handlerMethod.getMethod();

        HttpSession session =  request.getSession(false);
        //不能注册了
        if(session!=null){
            User hrUsers = (User) session.getAttribute("principal");
            if(hrUsers!=null) {
                //拦截权限
                Permission annotation = method1.getAnnotation(Permission.class);
                List<Menu> menu = (List<Menu>) session.getAttribute("menu");
                if (menu!=null&&annotation!=null) {
                    int flag=0;
                    for (Menu menu1 : menu) {
                        String url = menu1.getUrl();
                            if (url.equals(annotation.value()) && url != "" && annotation.value() != "") {
                                flag=1;
                            return true;
                        }
                    }
                    if(flag==0){
                        response.sendRedirect("/");
                    }
                } else {
                    session.invalidate();
                }
            }
        }
        //重定向
        response.sendRedirect("/");
        return false;
    }
}

