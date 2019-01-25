package little.princess.sys.manager.clientsys.web.mvc;


import com.alibaba.fastjson.JSON;
import little.princess.sys.manager.clientsys.web.tl.WebToeknContext;
import little.princess.sys.manager.web.token.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token拦截器
 *
 * @author: murasakiseifu
 * @date: 2018/12/21 11:58 AM}
 * @version: V1.0
 * @review: murasakiseifu/2018/12/21 11:58 AM}
 */
@Component
public class XTokenInterceptor extends HandlerInterceptorAdapter {

    private Logger log = LoggerFactory.getLogger(XTokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        log.info("requestURI:{}", uri);
        try {
            String requestMethod = request.getMethod();
            if ("OPTIONS".equals(requestMethod)) {
                return true;
            }
            if (!(handler instanceof HandlerMethod)) {
                return true;
            }
//            // 从切点上获取目标方法
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Method method = handlerMethod.getMethod();
//            // 若目标方法忽略了安全性检查，则直接调用目标方法
//            if (method.isAnnotationPresent(IgnoreToken.class)) {
//                return true;
//            }
            // 校验token
            String token = request.getHeader("X-Token");
            log.info("传入的token值:{}", token);
            if (StringUtils.isEmpty(token) || !TokenUtil.isValid(token)) {
                log.info("token校验错误");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().append(JSON.toJSONString(new AuthorizedBody("401", "token Check the error")));
                return false;
            }
            WebToeknContext.initToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    class AuthorizedBody {

        AuthorizedBody(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
