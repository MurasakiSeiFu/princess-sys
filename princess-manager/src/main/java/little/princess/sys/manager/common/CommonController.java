package little.princess.sys.manager.common;

import javassist.NotFoundException;
import little.princess.sys.manager.core.dto.LoginUserToken;
import little.princess.sys.manager.web.tl.WebToeknContext;
import little.princess.sys.manager.web.token.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基类
 *
 * @author: murasakiseifu
 * @date: 2018/12/21 2:31 PM}
 * @version: V1.0
 * @review: murasakiseifu/2018/12/21 2:31 PM
 */
public class CommonController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 判断对象是否为空,如果为空将抛出 {@link NotFoundException}
     *
     * @param obj
     * @param msg
     * @throws NotFoundException
     */
    protected void assertFound(Object obj, String msg) throws NotFoundException {
        if (obj == null) {
            throw new NotFoundException(msg);
        }
    }

    /**
     * 解析token
     * @param jwsToken
     * @return
     */
    public LoginUserToken unsign(String jwsToken) {
        LoginUserToken loginUserToken = null;
        try {
            loginUserToken = TokenUtil.unsign(jwsToken, LoginUserToken.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loginUserToken;
    }

    /**
     * 解析token
     * @param
     * @return
     */
    public LoginUserToken unsign() {
        LoginUserToken loginUserToken = null;
        try {
            loginUserToken = WebToeknContext.getToken(LoginUserToken.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loginUserToken;
    }
}

