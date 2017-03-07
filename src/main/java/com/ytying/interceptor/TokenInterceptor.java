package com.ytying.interceptor;

import com.ytying.cache.TokenCache;
import com.ytying.entity.UserAccount;
import com.ytying.packaged.ResultDo;
import com.ytying.sysenum.ReturnCode;
import com.ytying.util.JsonUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by kefan.wkf on 17/1/20.
 * token过滤器
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getParameter("token");
        int uid = Integer.parseInt(request.getParameter("uid"));
        UserAccount account = TokenCache.get(token);

        if (null == token || null == account || account.getUid() != uid) {
            setResponseContent(response, ReturnCode.RETURN_NOTOKEN_ERROR);
            return false;
        }

        if((account.getSurvive_time() + account.getC_time()) < new Date().getTime()){
            TokenCache.remove(account);
            setResponseContent(response,ReturnCode.RETURN_TOKEN_OUTDATE);
            return false;
        }

        account.setSurvive_time(new Date().getTime() - account.getC_time() + account.getSurvive_time());

        return true;
    }

    private static void setResponseContent(HttpServletResponse response, ReturnCode code) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Content-Type", "application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(JsonUtils.Object2Json(new ResultDo(code, null)));
        out.flush();
        out.close();
        response.setStatus(500);
    }
}
