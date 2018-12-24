package little.princess.sys.manager.core.dto;

import little.princess.sys.manager.web.token.Token;

/**
 * Token构建 实体entity
 *
 * @author: murasakiseifu
 * @date: 2018/12/21 11:58 AM}
 * @version: V1.0
 * @review: murasakiseifu/2018/12/21 11:58 AM}
 */
public class LoginUserToken extends Token {

    private Long userId;

    private String loginName;

    private String passwd;

    private String roleCode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
