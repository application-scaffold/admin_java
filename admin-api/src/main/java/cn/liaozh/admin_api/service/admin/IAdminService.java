package cn.liaozh.admin_api.service.admin;

import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.system.SystemAdminCreateValidate;
import cn.liaozh.admin_api.validate.system.SystemAdminSearchValidate;
import cn.liaozh.admin_api.validate.system.SystemAdminUpInfoValidate;
import cn.liaozh.admin_api.validate.system.SystemAdminUpdateValidate;
import cn.liaozh.admin_api.vo.auth.AdminMySelfVo;
import cn.liaozh.admin_api.vo.system.SystemAuthAdminDetailVo;
import cn.liaozh.admin_api.vo.system.SystemAuthAdminListedVo;
import cn.liaozh.admin_api.vo.system.SystemAuthAdminSelvesVo;
import cn.liaozh.common.core.PageResult;

import java.util.List;

/**
 * 系统管理员服务接口类
 */
public interface IAdminService {

    /**
     * 管理员列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<SystemAuthAdminListedVo>
     */
    PageResult<SystemAuthAdminListedVo> list(PageValidate pageValidate, SystemAdminSearchValidate searchValidate);

    /**
     * 当前管理员
     *
     * @author fzr
     * @return SystemSelfVo
     */
    SystemAuthAdminSelvesVo self(Integer adminId);

    /**
     * 管理员详情
     *
     * @author fzr
     * @param id 主键参数
     * @return SystemAuthAdminDetailVo
     */
    SystemAuthAdminDetailVo detail(Integer id);

    /**
     * 管理员新增
     *
     * @author fzr
     * @param createValidate 参数
     */
    void add(SystemAdminCreateValidate createValidate);

    /**
     * 管理员编辑
     *
     * @author fzr
     * @param updateValidate 参数
     * @param adminId 管理员ID
     */
    void edit(SystemAdminUpdateValidate updateValidate, Integer adminId);

    /**
     * 当前管理员更新
     *
     * @author fzr
     * @param upInfoValidate 参数
     * @param adminId 管理员ID
     */
    void upInfo(SystemAdminUpInfoValidate upInfoValidate, Integer adminId);

    /**
     * 管理员删除
     *
     * @author fzr
     * @param id 主键参数
     * @param adminId 管理员ID
     */
    void del(Integer id, Integer adminId);

    /**
     * 管理员状态切换
     *
     * @author fzr
     * @param id 主键参数
     */
    void disable(Integer id, Integer adminId);

    /**
     *  查看管理员详情
     * @param id
     * @return
     */
    AdminMySelfVo mySelf(Integer id, List<Integer> roleIds);

    /**
     * 返回导出格式
     * @return
     */
    JSONObject getExportData(PageValidate pageValidate, SystemAdminSearchValidate searchValidate);

    /**
     * 导出
     */
    String export(SystemAdminSearchValidate searchValidate);
}
