package com.neusoft.hr.sys.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.neusoft.hr.sys.converter.DateConverter;
import com.neusoft.hr.sys.interceptor.LoginInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import javax.sql.DataSource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
@EnableWebMvc
@PropertySource("classpath:jdbc.properties")
@ComponentScan(basePackages = "com.neusoft.hr")
@MapperScan(basePackages="com.neusoft.hr.business.responsitory")
public class SpringConfig extends WebMvcConfigurerAdapter {

//    @Autowired
//    public Environment environment;



    //配置视图解析器
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setCharacterEncoding("utf-8");
        //templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        //取消缓存
        templateResolver.setCacheable(false);
        return templateResolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver thyresolver = new ThymeleafViewResolver();
        thyresolver.setTemplateEngine(templateEngine());
        thyresolver.setCharacterEncoding("utf-8");
        //order  越大优先级越小
        thyresolver.setOrder(1);
        registry.viewResolver(thyresolver);
    }
    //mybatis
    //先配置datasource
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();

//        druidDataSource.setDriverClassName(environment.getProperty("jdbc.driverClass"));
//        druidDataSource.setUrl(environment.getProperty("jdbc.url"));
//        druidDataSource.setPassword(environment.getProperty("jdbc.password"));
//        druidDataSource.setUsername(environment.getProperty("jdbc.username"));
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/hr_manager?useUnicode=true&characterEncoding=utf8");
        druidDataSource.setPassword("");
        druidDataSource.setUsername("root");
        return druidDataSource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis-config.xml"));
        //指定扫描位子
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));
        return  sqlSessionFactoryBean.getObject();
    }
    //事务管理器
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    //拦截器 拦截是否登录
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      InterceptorRegistration interceptorRegistration = registry.addInterceptor(new LoginInterceptor());
       interceptorRegistration.addPathPatterns("/**").excludePathPatterns("/","/login");
   }

    //form表单日期格式 pojo格式转换
    @Override
    public void addFormatters(FormatterRegistry registry) {
        //配置DateConverter
        registry.addConverter(new DateConverter());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.dateFormat(new SimpleDateFormat("yyyy-mm-dd HH:dd:ss"));

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
        //当是date时候  json是long类型  不合适显示页面
        converters.add(converter);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/file/**").addResourceLocations("classpath:/file/");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");

        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
        super.addResourceHandlers(registry);
    }




}
