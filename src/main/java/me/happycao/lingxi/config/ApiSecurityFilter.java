package me.happycao.lingxi.config;

import com.alibaba.fastjson.JSON;
import me.happycao.lingxi.entity.TUser;
import me.happycao.lingxi.result.Result;
import me.happycao.lingxi.service.UserService;
import me.happycao.lingxi.util.DigestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author happyc
 * Api安全过滤
 */
@Component
public class ApiSecurityFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";

    @Resource
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestUri = request.getRequestURI();
        logger.info("url is :{}", requestUri);

        boolean notVerify = requestUri.contains("register") || requestUri.contains("reset") || requestUri.contains("login");

        if (notVerify) {
            filterChain.doFilter(request, servletResponse);
        } else {
            // 安全认证
            String token = request.getHeader("X-App-Token");
            boolean isSuccess;
            if (token == null) {
                isSuccess = false;
            } else {
                String[] split = token.split(DigestUtil.AT);
                if (split.length == DigestUtil.TOKEN_ELEMENTS_NUM) {
                    String userId = split[0];
                    request.setAttribute("userId", userId);
                    TUser tUser = userService.userInfo(userId);
                    if (tUser == null) {
                        isSuccess = false;
                    } else {
                        Integer state = tUser.getState();
                        if (state != 1) {
                            isSuccess = false;
                        } else {
                            // 校验token正确性
                            String sign = DigestUtil.generatedToken(userId, tUser.getPassword());
                            isSuccess = sign.equalsIgnoreCase(token);
                        }
                    }
                } else {
                    isSuccess = false;
                }
            }
            if (isSuccess) {
                filterChain.doFilter(request, servletResponse);
            } else {
                setErrorResponse(servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 设置错误提示
     */
    private void setErrorResponse(ServletResponse servletResponse) throws IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setStatus(403);
        response.setContentType(CONTENT_TYPE_JSON);
        response.getWriter().print(JSON.toJSONString(Result.error("00403", "安全验证失败")));
    }

}
