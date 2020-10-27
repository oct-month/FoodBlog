package cn.ablocker.FoodBlog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class HtmlInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String requestURL = request.getRequestURL().toString();
        // String remoteAddr = request.getHeader("X-real-ip");
        // String remoteAddr = request.getRemoteAddr();
        // if (remoteAddr.equals("0:0:0:0:0:0:0:1") || remoteAddr.equals("127.0.0.1"))
        //     return true;
        // TODO 拦截 .html 请求
        // if (requestURL.endsWith(".html"))
        // {
        //     return false;
        // }
        return true;
    }
}
