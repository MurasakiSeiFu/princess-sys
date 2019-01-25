package little.princess.sys.manager.core.mapper;

import little.princess.sys.manager.core.entity.CUser;
import org.apache.ibatis.annotations.Param;

/**
 * 登录用户表 mapper接口
 *
 * @author: murasakiseifu
 * @date: 2019/1/9 3:37 PM}
 * @version: V1.0
 * @review: murasakiseifu/2019/1/9 3:37 PM}
 */
public interface CUserMapper {

    /**
     * 根据账号和密码查询用户
     *
     * @param loginName
     * @param passwd
     * @return
     */
    CUser findByNameAndPasswd(@Param("loginName") String loginName, @Param("passwd") String passwd);

    /**
     * 根据UserId获取用户信息
     *
     * @param userId
     * @return
     */
    CUser findByUserId(@Param("userId") Long userId);

    /**
     * 注册
     *
     * @param cUser
     * @return
     */
    Integer register(CUser cUser);
}
