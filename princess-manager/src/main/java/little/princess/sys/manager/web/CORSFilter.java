package little.princess.sys.manager.web;

import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CORS跨域配置
 *
 * @date: 2018/8/15 下午2:58}
 * @version: V1.0
 */
@Order(1)
@WebFilter(filterName = "CorsFilter", urlPatterns = "/*")
public class CORSFilter implements Filter {

    //初始化 获取FilterConfig对象可以得到ServletContext对象，以及部署描述符中配置的过滤器的初始化参数。
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //当客户端请求目标资源的时候，容器就会调用与这个目标资源相关联的过滤器的 doFilter()方法。
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse responseHttp = (HttpServletResponse) response;
        responseHttp.setHeader("Access-Control-Allow-Origin", "*");
        responseHttp.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE");
        responseHttp.setHeader("Access-Control-Max-Age", "3600");
        responseHttp.setHeader("Access-Control-Allow-Headers",
                "Content-Type, Accept, X-Requested-With, remember-me, X-Token");
        chain.doFilter(request, response);
    }

    //在Web容器卸载 Filter 对象之前被调用。该方法在Filter的生命周期中仅执行一次。在这个方法中，可以释放过滤器使用的资源。
    @Override
    public void destroy() {

    }
}
