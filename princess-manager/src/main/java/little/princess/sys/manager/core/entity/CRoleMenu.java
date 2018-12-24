package little.princess.sys.manager.core.entity;

/**
 * 实体Entity
 *
 * @author: murasakiseifu
 * @date: 2018/8/15 下午2:50}
 * @version: V1.0
 * @review: murasakiseifu/2018/8/15 下午2:50}
 */
public class CRoleMenu implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键.
     */
    private Long id;

    /**
     * 角色id.
     */
    private String roleCode;

    /**
     * 菜单code.
     */
    private String menuCode;

    /**
     * 主键 get方法.
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键 set方法.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 角色id get方法.
     */
    public String getRoleCode() {
        return roleCode == null ? null : roleCode.trim();
    }

    /**
     * 角色id set方法.
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    /**
     * 菜单code get方法.
     */
    public String getMenuCode() {
        return menuCode == null ? null : menuCode.trim();
    }

    /**
     * 菜单code set方法.
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

}

