package com.yxl.enrollment.Config;

import com.yxl.enrollment.Conponent.AdminHandlerInterceptor;
import com.yxl.enrollment.Conponent.LockHandlerInterceptor;
import com.yxl.enrollment.Conponent.LoginHandlerInterceptor;
import com.yxl.enrollment.Conponent.VerificationCodeHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index","/error","/sign/**","/css/**","/fonts/**","/images/**","/js/**","/pages/**","/plugins/**","/error/**");
        registry.addInterceptor(new LockHandlerInterceptor()).addPathPatterns("/admin/**","/admin").excludePathPatterns("/css/**","/fonts/**","/images/**","/js/**","/pages/**","/plugins/**");
        registry.addInterceptor(new AdminHandlerInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin");
        //验证码 有空实现
        //        registry.addInterceptor(new VerificationCodeHandlerInterceptor()).addPathPatterns("/sign/student","/sign/tutor","/sign/admin","/sign/signup");
    }

}
