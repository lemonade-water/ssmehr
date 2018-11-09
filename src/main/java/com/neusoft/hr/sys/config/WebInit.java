package com.neusoft.hr.sys.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.*;
import java.util.EnumSet;

public class WebInit implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfig.class);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        FilterRegistration.Dynamic encoding = servletContext.addFilter("encoding",new CharacterEncodingFilter("utf-8"));
        encoding.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");

        servletContext.setAttribute("root",servletContext.getContextPath());

    }
}
