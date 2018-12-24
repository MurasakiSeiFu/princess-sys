package little.princess.sys.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 后台管理启动类
 *
 * @author: murasakiseifu
 * @date: 2018/12/21 10:35 AM}
 * @version: V1.0
 * @review: murasakiseifu/2018/12/21 10:35 AM}
 */
@ServletComponentScan
@SpringBootApplication
@MapperScan("little.princess.sys.manager.core.mapper")
public class ManagerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ManagerApplication.class);
        app.run(args);
        LOGGER.info(" Welcome my lovely little princess！👸");
    }
}
