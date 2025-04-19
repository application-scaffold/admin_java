package cn.liaozh.admin_api.service.impl.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.liaozh.admin_api.service.admin.IAdminRoleService;
import cn.liaozh.common.entity.admin.AdminRole;
import cn.liaozh.common.mapper.admin.AdminRoleMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统角色服务实现类
 */
@Service
public class AdminRoleServiceImpl implements IAdminRoleService {

    @Resource
    AdminRoleMapper adminRoleMapper;
    @Override
    public List<Integer> getRoleIdAttr(Integer adminId) {
        List<Integer> ret = new ArrayList<>();
        List<AdminRole> rolesList = adminRoleMapper.selectList(new QueryWrapper<AdminRole>().eq("admin_id", adminId).select("role_id"));
        if (rolesList.size() > 0) {
            for (AdminRole adminRole : rolesList) {
                ret.add(adminRole.getRoleId());
            }
        }
        return ret;
    }

    @Override
    public List<AdminRole> getAdminIdByRoleId(Integer roleId) {
        List<AdminRole> result = adminRoleMapper.selectList(new QueryWrapper<AdminRole>().eq("role_id", roleId).select("admin_id"));
        return result;
    }

    @Override
    public void batchInsert(Integer adminId, List<Integer> roleIds) {
        this.deleteByAdminId(adminId);
        if (roleIds != null && !roleIds.isEmpty()) {
            roleIds.forEach(item-> {
                this.adminRoleMapper.insert(new AdminRole(){{
                    setAdminId(adminId);
                    setRoleId(item);
                }});
            });
        }
    }

    @Override
    public void deleteByAdminId(Integer adminId) {
        this.adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("admin_id", adminId));
    }
}
