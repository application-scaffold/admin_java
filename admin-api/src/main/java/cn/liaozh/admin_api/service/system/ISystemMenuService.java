package cn.liaozh.admin_api.service.system;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.validate.system.SystemMenuCreateValidate;
import cn.liaozh.admin_api.validate.system.SystemMenuUpdateValidate;
import cn.liaozh.admin_api.vo.system.SystemAuthMenuVo;

import java.util.List;

/**
 * 系统菜单服务接口类
 */
public interface ISystemMenuService {

    /**
     * 根据角色获取菜单
     *
     * @author fzr
     * @return JSONArray
     */
    JSONArray selectMenuByRoleId(List<Integer> roleId);

    /**
     * 菜单列表
     *
     * @author fzr
     * @return JSONArray
     */
    JSONObject list();

    /**
     * 菜单列表
     *
     * @author fzr
     * @return JSONArray
     */
    JSONArray systemMenuLists();

    /**
     * 菜单列表
     *
     * @author fzr
     * @return JSONArray
     */
    JSONArray all();

    /**
     * 菜单详情
     *
     * @author fzr
     * @param id 主键
     * @return SysMenu
     */
    SystemAuthMenuVo detail(Integer id);

    /**
     * 菜单新增
     *
     * @author fzr
     * @param createValidate 参数
     */
    void add(SystemMenuCreateValidate createValidate);

    /**
     * 菜单编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    void edit(SystemMenuUpdateValidate updateValidate);

    /**
     * 菜单删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

}
