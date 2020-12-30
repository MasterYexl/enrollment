package com.yxl.enrollment.Conponent;

import com.yxl.enrollment.Module.VCMod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerificationCodeHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ((boolean)request.getSession().getAttribute("vc")){
            VCMod vcMod = (VCMod) request.getSession().getAttribute("vcCode");
            String code = request.getParameter("verify");
            if (vcMod.getCode().equals(code)) return true;
        }
        request.getSession().setAttribute("msg", "验证码错误");
        request.getRequestDispatcher(request.getRequestURI()).forward(request,response);
        return false;
    }
}
