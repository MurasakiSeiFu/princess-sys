package little.princess.sys.manager.web.tl;

import little.princess.sys.manager.web.token.Token;
import little.princess.sys.manager.web.token.TokenUtil;

/**
 * @author: murasakiseifu
 * @date: 2018/12/21 2:18 PM}
 * @version: V1.0
 * @review: murasakiseifu/2018/12/21 2:18 PM}
 */
public class WebToeknContext {

    private static ThreadLocal<String> TOKENHODLER = new ThreadLocal<String>();

    /**
     * initToken
     *
     * @date 2018/8/9 下午3:37
     * @param: [tokenId]
     * @return: void
     */
    public static void initToken(String tokenId) {
        TOKENHODLER.set(tokenId);
    }

    public static ThreadLocal<String> getTokenHodler() {
        return TOKENHODLER;
    }

    public static void setTokenHodler(ThreadLocal<String> tokenHodler) {
        WebToeknContext.TOKENHODLER = tokenHodler;
    }

    /**
     * getToken
     *
     * @date 2018/8/9 下午3:37
     * @param: [classT]
     * @return: T
     */
    public static <T extends Token> T getToken(Class<T> classT) throws Exception {
        return TokenUtil.unsign(TOKENHODLER.get(), classT);
    }

}
