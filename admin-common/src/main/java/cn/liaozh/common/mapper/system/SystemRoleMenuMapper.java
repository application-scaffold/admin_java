package cn.liaozh.common.mapper.system;

import cn.liaozh.common.core.basics.IBaseMapper;
import cn.liaozh.common.entity.system.SystemRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 系统菜单Mapper
 */
@Mapper
public interface SystemRoleMenuMapper extends IBaseMapper<SystemRoleMenu> {


    @Select("SELECT DISTINCT menuR.menu_id FROM la_system_role_menu menuR INNER JOIN la_system_role role ON menuR.role_id = role.id INNER JOIN la_admin_role lar ON lar.role_id = role.id WHERE lar.admin_id = #{adminId} AND role.delete_time IS NULL")
    List<Integer> getMenuIds(@Param("adminId") Integer adminId);



}
