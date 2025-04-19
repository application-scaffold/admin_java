package cn.liaozh.front_api.cache;

import cn.liaozh.common.util.RedisUtils;
import cn.liaozh.common.util.StringUtils;

/**
 * 微信扫码登录缓存
 */
public class ScanLoginCache {

    private static final String KEY = "wechat:scan:login:";

    public static String get(String sessionId) {
        Object o = RedisUtils.get(KEY+sessionId);
        if (StringUtils.isNull(o)) {
            return "";
        }

        RedisUtils.del(KEY+sessionId);
        return o.toString();
    }

    public static void set(String sessionId, String state) {
        RedisUtils.set(KEY+sessionId, state, 600);
    }

}
