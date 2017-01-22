package com.ytying.interceptor;

import com.ytying.cache.TokenCache;
import com.ytying.entity.UserAccount;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kefan.wkf on 17/1/20.
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getParameter("token");
        UserAccount account = TokenCache.get(token);

        if(null == token || account == null){

        }

        return super.preHandle(request, response, handler);
    }
}
