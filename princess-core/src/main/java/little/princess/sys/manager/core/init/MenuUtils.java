package little.princess.sys.manager.core.init;

import little.princess.sys.manager.core.entity.CMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 菜单初始化
 *
 * @author: murasakiseifu
 * @date: 2019/1/9 5:50 PM}
 * @version: V1.0
 * @review: murasakiseifu/2019/1/9 5:50 PM}
 */
public class MenuUtils {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * ROLE_MENU : 角色---对应菜单
     */
    public static Map<String, CMenu> ROLE_MENU = new HashMap<>();

    /**
     * INSTANCE
     */
    public static MenuUtils INSTANCE;

    /**
     * 菜单初始化 单例静态工厂
     * @return
     */
    public static MenuUtils getINSTANCE() {
        if (INSTANCE == null) {
            return new MenuUtils();
        }
        return INSTANCE;
    }

    /**
     * 初始化方法
     *
     * @author murasakiseifu
     * @date 2019/1/9 5:53 PM
     * @param: []
     * @return: void
     */
    public void init() {
        logger.info("MenuUtils init");
        loadRoleMenu();
    }

    /**
     * 生成菜单树
     *
     * @author murasakiseifu
     * @date 2019/1/9 5:54 PM
     * @param: []
     * @return: void
     */
    private void loadRoleMenu() {

    }
}
