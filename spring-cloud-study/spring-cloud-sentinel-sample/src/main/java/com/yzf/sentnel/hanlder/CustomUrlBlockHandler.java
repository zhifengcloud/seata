package com.yzf.sentnel.hanlder;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义流控返回输出
 */
@Component
public class CustomUrlBlockHandler implements UrlBlockHandler{
    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
        String message = "{\"code\":999,\"msg\":\"访问人数过多\"}";
        httpServletResponse.getWriter().write(message);
    }
}

