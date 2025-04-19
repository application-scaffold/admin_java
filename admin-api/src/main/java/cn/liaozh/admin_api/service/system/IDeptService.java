package cn.liaozh.admin_api.service.system;

import com.alibaba.fastjson2.JSONArray;
import cn.liaozh.admin_api.validate.system.DeptCreateValidate;
import cn.liaozh.admin_api.validate.system.DeptSearchValidate;
import cn.liaozh.admin_api.validate.system.DeptUpdateValidate;
import cn.liaozh.admin_api.vo.system.DeptVo;

/**
 * 系统部门服务接口类
 */
public interface IDeptService {

    /**
     * 部门列表
     *
     * @author fzr
     * @param searchValidate 搜索参数
     * @return JSONArray
     */
    JSONArray list(DeptSearchValidate searchValidate);

    /**
     * 部门所有
     *
     * @author fzr
     * @return JSONArray
     */
    JSONArray all();

    /**
     * 部门详情
     *
     * @author fzr
     * @param id 主键
     * @return SysMenu
     */
    DeptVo detail(Integer id);

    /**
     * 部门新增
     *
     * @author fzr
     * @param createValidate 参数
     */
    void add(DeptCreateValidate createValidate);

    /**
     * 部门编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    void edit(DeptUpdateValidate updateValidate);

    /**
     * 部门删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

}
