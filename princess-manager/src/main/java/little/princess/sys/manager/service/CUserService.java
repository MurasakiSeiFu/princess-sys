package little.princess.sys.manager.service;

import little.princess.sys.manager.core.entity.CUser;

/**
 * 登录用户表 服务接口
 *
 * @author: murasakiseifu
 * @date: 2019/1/9 3:39 PM}
 * @version: V1.0
 * @review: murasakiseifu/2019/1/9 3:39 PM}
 */
public interface CUserService {

    /**
     * 根据账号和密码查询用户
     * @param loginName
     * @param passwd
     * @return
     */
    CUser findByNameAndPasswd(String loginName, String passwd);

    /**
     * 根据UserId获取用户信息
     *
     * @param userId
     * @return
     */
    CUser findByUserId(Long userId);

    /**
     * 注册
     *
     * @param cUser
     * @return
     */
    Integer register(CUser cUser);
}
