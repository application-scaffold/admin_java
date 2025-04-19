package cn.liaozh.admin_api.service.impl.system;

import cn.liaozh.admin_api.service.system.ISystemCacheService;
import cn.liaozh.common.cache.ConfigCache;
import org.springframework.stereotype.Service;

/**
 * 系统缓存实现类
 */
@Service
public class SystemCacheServiceImpl implements ISystemCacheService {

    /**
     * 清除系统缓存
     */
    @Override
    public void clear() {
        ConfigCache.clear();
    }


}
