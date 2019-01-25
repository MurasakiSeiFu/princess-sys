package little.princess.sys.manager.clientsys.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2配置
 *
 * @date: 2018/8/15 下午2:58}
 * @version: V1.0
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    /**
     * 创建RestApi
     *
     * @date 2018/8/15 下午3:13
     * @param: []
     * @return: springfox.documentation.spring.web.plugins.Docket
     */
    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("X-Token").description("Token").modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).globalOperationParameters(pars).select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("little.princess.sys.manager.controller"))
                .paths(PathSelectors.any()).build();
    }

    /**
     * swagger2信息
     *
     * @date 2018/8/15 下午3:13
     * @param: []
     * @return: springfox.documentation.service.ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("little princess service").contact(new Contact("MurasakiSeiFu", "", ""))
                .version("1.0").description("little princess service desc").build();
    }
}
