package little.princess.sys.clientsys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: murasakiseifu
 * @date: 2019/1/25 10:40 AM}
 * @version: V1.0
 * @review: murasakiseifu/2019/1/25 10:40 AM}
 */
@SpringBootApplication
public class ClientSysApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientSysApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ClientSysApplication.class);
        app.run(args);
        LOGGER.info(" Welcome my lovely little princess！👸");
        LOGGER.info(" Congratulations, you have successfully launched the personal homepage system! 🍻");
    }
}
