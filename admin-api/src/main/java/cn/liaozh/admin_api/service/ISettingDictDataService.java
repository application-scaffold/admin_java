package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.setting.DictDataCreateValidate;
import cn.liaozh.admin_api.validate.setting.DictDataUpdateValidate;
import cn.liaozh.admin_api.vo.setting.SettingDictDataVo;
import cn.liaozh.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 字典数据服务接口类
 */
public interface ISettingDictDataService {

    /**
     * 字典数据所有
     *
     * @author fzr
     * @return List<DictDataVo>
     */
    List<SettingDictDataVo> all(Map<String, String> params);

    /**
     * 字典数据列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param params 搜索参数
     * @return PageResult<DictDataVo>
     */
    PageResult<SettingDictDataVo> list(PageValidate pageValidate, Map<String, String> params);

    /**
     * 字典数据详情
     *
     * @author fzr
     * @param id 主键
     * @return DictDataVo
     */
    SettingDictDataVo detail(Integer id);

    /**
     * 字典数据新增
     *
     * @author fzr
     * @param createValidate 参数
     */
    void add(DictDataCreateValidate createValidate);

    /**
     * 字典数据编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    void edit(DictDataUpdateValidate updateValidate);

    /**
     * 字典数据删除
     *
     * @author fzr
     * @param ids 主键
     */
    void del(List<Integer> ids);

}
