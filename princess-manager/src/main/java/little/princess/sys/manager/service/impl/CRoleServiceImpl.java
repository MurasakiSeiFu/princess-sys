package little.princess.sys.manager.service.impl;

import little.princess.sys.manager.core.mapper.CRoleMapper;
import little.princess.sys.manager.service.CRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色表 服务实现类
 *
 * @author: murasakiseifu
 * @date: 2019/1/9 6:05 PM}
 * @version: V1.0
 * @review: murasakiseifu/2019/1/9 6:05 PM}
 */
@Service
public class CRoleServiceImpl implements CRoleService {

    @Autowired
    private CRoleMapper cRoleMapper;
}
