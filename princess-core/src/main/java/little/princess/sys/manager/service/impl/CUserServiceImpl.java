package little.princess.sys.manager.service.impl;

import little.princess.sys.common.all.BaseParamEntity;
import little.princess.sys.manager.core.entity.CUser;
import little.princess.sys.manager.core.mapper.CUserMapper;
import little.princess.sys.manager.service.CUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录用户表 服务实现类
 *
 * @author: murasakiseifu
 * @date: 2019/1/9 3:39 PM}
 * @version: V1.0
 * @review: murasakiseifu/2019/1/9 3:39 PM}
 */
@Service
public class CUserServiceImpl implements CUserService {

    @Autowired
    private CUserMapper cUserMapper;

    @Override
    public CUser findByNameAndPasswd(String loginName, String passwd) {
        return cUserMapper.findByNameAndPasswd(loginName, passwd);
    }

    @Override
    public CUser findByUserId(Long userId) {
        return cUserMapper.findByUserId(userId);
    }

    @Override
    public Integer register(CUser cUser) {
        cUser.setRoleCode(BaseParamEntity.CLIENT);
        return cUserMapper.register(cUser);
    }

}
