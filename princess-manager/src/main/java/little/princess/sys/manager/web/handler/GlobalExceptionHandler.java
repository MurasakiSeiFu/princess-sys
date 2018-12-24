package little.princess.sys.manager.web.handler;

import little.princess.sys.manager.exception.BaseException;
import little.princess.sys.manager.exception.ExceptionCode;
import little.princess.sys.manager.web.message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * web层异常拦截
 *
 * @author: murasakiseifu
 * @date: 2018/12/21 11:40 AM}
 * @version: V1.0
 * @review: murasakiseifu/2018/12/21 11:40 AM}
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 拦截异常返回信息
     *
     * @author murasakiseifu
     * @date  11:49 AM
     * @param: [exception]
     * @return: little.princess.sys.manager.web.message.ResponseMessage
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage inceptor(Exception exception) {
        if (exception instanceof BaseException) {
            logger.info(exception.getMessage(), exception);
            BaseException e = (BaseException) exception;
            return ResponseMessage.error(e.getMessage(),
                    Integer.valueOf(e.getMessageCode() != null ? e.getMessageCode() : "0"));
        }
        logger.error(exception.getMessage(), exception);
        return ResponseMessage.error(exception.getMessage(), ExceptionCode.EXCEPTION_SYSTEM);
    }
}
