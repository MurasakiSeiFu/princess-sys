package little.princess.sys.manager.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import little.princess.sys.manager.core.dto.LoginUserToken;
import little.princess.sys.manager.core.entity.CUser;
import little.princess.sys.manager.exception.ExceptionCode;
import little.princess.sys.manager.service.CUserService;
import little.princess.sys.manager.web.message.ResponseMessage;
import little.princess.sys.manager.web.tl.WebToeknContext;
import little.princess.sys.manager.web.token.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static little.princess.sys.manager.web.message.ResponseMessage.ok;

/**
 * 登录Controller
 *
 * @author: murasakiseifu
 * @date: 2019/1/9 3:36 PM}
 * @version: V1.0
 * @review: murasakiseifu/2019/1/9 3:36 PM}
 */
@RestController
@Api(description = "登录管理")
@RequestMapping("/princess")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private CUserService cUserService;

    /**
     * 用户登录
     *
     * @author murasakiseifu
     * @date 2019/1/9 3:48 PM
     * @param: [loginName, passwd]
     * @return: little.princess.sys.manager.web.message.ResponseMessage
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public ResponseMessage login(@RequestParam(required = true) String loginName,
                                 @RequestParam(required = true) String passwd) throws Exception {
        LoginUserToken loginUserToken = new LoginUserToken();
        // 登录校验
        CUser cUser = cUserService.findByNameAndPasswd(loginName, passwd);
        if (cUser == null) {
            return ResponseMessage.error("Login error", ExceptionCode.EXCEPTION_LOGIN);
        } else if (cUser.getState() == 0) {
            return ResponseMessage.error("对不起,您被禁止登陆。", ExceptionCode.EXCEPTION_LOGIN);
        }
        loginUserToken.setUserId(cUser.getId());
        loginUserToken.setLoginName(cUser.getLoginname());
        loginUserToken.setRoleCode(cUser.getRoleCode());
        // 生成token
        String token = TokenUtil.sign(loginUserToken);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return ok(map);
    }

    /**
     * 当前登录用户信息
     *
     * @author murasakiseifu
     * @date 2019/1/9 4:19 PM
     * @param: []
     * @return: little.princess.sys.manager.web.message.ResponseMessage
     */
    @RequestMapping(value = "/getuserinfo", method = RequestMethod.GET)
    @ApiOperation(value = "当前登录用户信息", notes = "当前登录用户信息")
    public ResponseMessage getUserInfo() throws Exception {
        // 通过token拦截 取到token
        LoginUserToken loginUserToken = WebToeknContext.getToken(LoginUserToken.class);
        Map<String, Object> map = new HashMap<>();
        CUser cUser = cUserService.findByUserId(loginUserToken.getUserId());
        map.put("userId", loginUserToken.getUserId());
        map.put("loginName", loginUserToken.getLoginName());
        map.put("nickName", cUser.getNickname());
        map.put("roleCode", loginUserToken.getRoleCode());
        return ok(map);
    }

    /**
     * 注册
     *
     * @author murasakiseifu
     * @date 2019/1/9 5:08 PM
     * @param: [cUser]
     * @return: little.princess.sys.manager.web.message.ResponseMessage
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "注册", notes = "注册")
    public ResponseMessage register(@RequestBody CUser cUser) {
        Integer number = cUserService.register(cUser);
        return ok(number);
    }
}
