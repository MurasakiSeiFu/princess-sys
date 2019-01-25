package little.princess.sys.manager.web.token;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: murasakiseifu
 * @date: 2018/12/21 11:58 AM}
 * @version: V1.0
 * @review: murasakiseifu/2018/12/21 11:58 AM}
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreToken {

}
