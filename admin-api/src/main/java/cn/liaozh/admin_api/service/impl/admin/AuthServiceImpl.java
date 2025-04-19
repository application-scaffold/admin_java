package cn.liaozh.admin_api.service.impl.admin;

import cn.liaozh.admin_api.service.admin.IAdminService;
import cn.liaozh.admin_api.service.admin.IAuthService;
import cn.liaozh.admin_api.vo.system.SystemAuthAdminDetailVo;
import cn.liaozh.common.mapper.system.SystemMenuMapper;
import cn.liaozh.common.mapper.system.SystemRoleMenuMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限功能类
 */
@Service
public class AuthServiceImpl implements IAuthService {

    @Resource
    IAdminService iAdminService;

    @Resource
    SystemMenuMapper systemMenuMapper;

    @Resource
    SystemRoleMenuMapper SystemRoleMenuMapper;

    @Override
    public List<String> getBtnAuthByRoleId(Integer adminId) {
        List<String> ret = new ArrayList<String>();
        SystemAuthAdminDetailVo admin = iAdminService.detail(adminId);
        if (admin.getRoot().equals(1)) {
            ret.add("*");
            return ret;
        } else {
            List<Integer> menuIds = SystemRoleMenuMapper.getMenuIds(adminId);
            ret = this.systemMenuMapper.getPerms(menuIds);
        }
        return ret;
    }
}
