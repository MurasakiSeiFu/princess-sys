package little.princess.sys.manager.web.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author: murasakiseifu
 * @date: 2018/12/21 11:58 AM}
 * @version: V1.0
 * @review: murasakiseifu/2018/12/21 11:58 AM}
 */
@Order(10)
@Configuration
public class WebMvcConfigAdapter extends WebMvcConfigurationSupport {

    /**
     * SWAGER_URL_PATTERNS
     */
    public static final String SWAGER_URL_PATTERNS = "/swagger*/**,/v2/api-docs";

    /**
     * LOGIN_URL_PATTERNS
     */
    public static final String LOGIN_URL_PATTERNS = "/princess/login,/princess/register";

    @Autowired
    private XTokenInterceptor xtokenInterceptor;

    //进一步配置已注册的拦截器，添加它应该应用的URL模式。
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(xtokenInterceptor).addPathPatterns("/**")
//                .excludePathPatterns(SWAGER_URL_PATTERNS.split(","))
//                .excludePathPatterns(LOGIN_URL_PATTERNS.split(","));
//    }

    //添加用于提供静态资源的资源处理程序。
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        //根据指定的URL路径模式添加用于提供静态资源的资源处理程序,
        //添加一个或多个资源位置,从中提供静态内容,每个位置都必须指向一个有效的目录。
        //可以将多个位置指定为以逗号分隔的列表，并按指定的顺序检查给定资源的位置。
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

    //解决js处理long型丢失精度问题
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //定义Json转换器
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //定义对象映射器
        ObjectMapper objectMapper = new ObjectMapper();
        //定义对象模型
        SimpleModule simpleModule = new SimpleModule();
        //添加对长整型的转换关系
        simpleModule.addSerializer(long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        //在转换器列表中添加自定义的Json转换器
        converters.add(jackson2HttpMessageConverter);
        super.configureMessageConverters(converters);
    }

}
