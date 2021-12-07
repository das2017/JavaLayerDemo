package org.zzj.common.auth;

import org.zzj.common.constants.ApiCode;
import org.zzj.common.custom.ApiException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ApiException {
        String token = request.getHeader("Authorization");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        if (method.isAnnotationPresent(AuthLogin.class)) {
            AuthLogin loginRequired = method.getAnnotation(AuthLogin.class);
            if (loginRequired.required()) {
                if (token == null) {
                    throw new ApiException(ApiCode.UNAUTHORIZED, ApiCode.UNAUTHMSG_DEFAULT);
                }
                if (AuthManager.verify(token)) {
                    return true;
                } else {
                    throw new ApiException(ApiCode.UNAUTHORIZED, ApiCode.UNAUTHMSG_DEFAULT);
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}