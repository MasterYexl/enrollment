package com.yxl.enrollment.Config;

import com.yxl.enrollment.Conponent.LockHandlerInterceptor;
import com.yxl.enrollment.Conponent.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index","/sign/**","/css/**","/fonts/**","/images/**","/js/**","/pages/**","/plugins/**");
        registry.addInterceptor(new LockHandlerInterceptor()).addPathPatterns("/admin/**","/admin").excludePathPatterns("/css/**","/fonts/**","/images/**","/js/**","/pages/**","/plugins/**");
    }

}
