package little.princess.sys.manager.core.entity;

import java.util.Date;

/**
 * 登陆用户表 实体Entity
 *
 * @author: MurasakiSeiFu
 * @date: 2019-01-09 15:34:11
 * @version: V1.0-auto
 * @review: MurasakiSeiFu/2019-01-09 15:34:11
 */
public class CUser implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**主键.*/
    private Long id;

    /**登陆账户.*/
    private String loginname;

    /**登陆密码.*/
    private String loginpw;

    /**昵称.*/
    private String nickname;

    /**邮箱.*/
    private String email;

    /**角色id.*/
    private String roleCode;

    /**状态 1正常，0禁止登陆，-1删除.*/
    private Integer state;

    /**备注.*/
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLoginpw() {
        return loginpw;
    }

    public void setLoginpw(String loginpw) {
        this.loginpw = loginpw;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CUser{" +
                "id=" + id +
                ", loginname='" + loginname + '\'' +
                ", loginpw='" + loginpw + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", state=" + state +
                ", remark='" + remark + '\'' +
                '}';
    }
}
