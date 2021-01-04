package com.yxl.enrollment.Conponent;

import com.yxl.enrollment.Module.SignState;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LockHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SignState signState = (SignState) request.getSession().getAttribute("signState");
        if (signState==null) return false;
        if (signState.getLock()){
//            System.out.println(request.getRequestURI()+"被拦截： LockHandlerInterceptor");
            request.getRequestDispatcher("/sign/lock").forward(request,response);
            return false;
        }
        return true;
    }
}
