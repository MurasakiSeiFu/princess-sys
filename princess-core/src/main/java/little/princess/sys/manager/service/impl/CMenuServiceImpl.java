package little.princess.sys.manager.service.impl;

import little.princess.sys.manager.core.mapper.CMenuMapper;
import little.princess.sys.manager.service.CMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 菜单服务实现类
 *
 * @author: murasakiseifu
 * @date: 2019/1/9 5:57 PM}
 * @version: V1.0
 * @review: murasakiseifu/2019/1/9 5:57 PM}
 */
@Service
public class CMenuServiceImpl implements CMenuService {

    @Autowired
    private CMenuMapper cMenuMapper;
}
