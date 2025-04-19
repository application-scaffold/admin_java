package cn.liaozh.admin_api.config.stp;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.liaozh.admin_api.LikeAdminThreadLocal;
import cn.liaozh.admin_api.service.admin.IAdminRoleService;
import cn.liaozh.common.entity.system.SystemMenu;
import cn.liaozh.common.entity.system.SystemRoleMenu;
import cn.liaozh.common.mapper.system.SystemMenuMapper;
import cn.liaozh.common.mapper.system.SystemRoleMenuMapper;
import cn.liaozh.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Sa-Token自定义权限验证接口
 */
@Component
public class StpInterConfig implements StpInterface {

    @Resource
    SystemRoleMenuMapper systemRoleMenuMapper;

    @Resource
    SystemMenuMapper systemAuthMenuMapper;

    @Resource
    IAdminRoleService iAdminRoleService;

    /**
     * 返回一个账号所拥有的权限码集合
     *
     * @param loginId 登录ID
     * @param loginType 登录类型
     * @return List<String>
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<Integer> roleIds  = LikeAdminThreadLocal.getRoleIds();
        List<String> perms = new LinkedList<>();
        if (roleIds.isEmpty()) {
            return perms;
        }

        List<SystemRoleMenu> permList = systemRoleMenuMapper.selectList(
                new QueryWrapper<SystemRoleMenu>()
                        .select("role_id,menu_id")
                        .in("role_id", roleIds));

        if (permList.isEmpty()) {
            return perms;
        }

        List<Integer> menuIds = new LinkedList<>();
        for (SystemRoleMenu systemAuthPerm : permList) {
            menuIds.add(systemAuthPerm.getMenuId());
        }

        List<SystemMenu> systemAuthMenus = systemAuthMenuMapper.selectList(
                new QueryWrapper<SystemMenu>()
                        .select("id,perms")
                        .eq("is_disable", 0)
                        .in("id", menuIds)
                        .in("type", Arrays.asList("C", "A"))
                        .orderByAsc(Arrays.asList("sort", "id")));

        for (SystemMenu item : systemAuthMenus) {
            if (StringUtils.isNotNull(item.getPerms()) && StringUtils.isNotEmpty(item.getPerms())) {
//                perms.add(item.getPerms().trim().replace("/", ":"));
                perms.add(item.getPerms().trim());
            }
        }

        return perms;
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     *
     * @param loginId 登录ID
     * @param loginType 登录类型
     * @return List<String>
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return null;
    }

}
