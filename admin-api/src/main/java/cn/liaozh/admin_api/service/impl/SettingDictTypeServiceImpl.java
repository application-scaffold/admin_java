package cn.liaozh.admin_api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.liaozh.admin_api.service.ISettingDictTypeService;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.setting.DictTypeCreateValidate;
import cn.liaozh.admin_api.validate.setting.DictTypeUpdateValidate;
import cn.liaozh.admin_api.vo.setting.SettingDictTypeVo;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.entity.setting.DictType;
import cn.liaozh.common.mapper.setting.DictTypeMapper;
import cn.liaozh.common.util.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 字典类型服务实现类
 */
@Service
public class SettingDictTypeServiceImpl implements ISettingDictTypeService {

    @Resource
    DictTypeMapper dictTypeMapper;

    /**
     * 字典类型所有
     *
     * @author fzr
     * @return List<DictTypeVo>
     */
    @Override
    public List<SettingDictTypeVo> all() {
        QueryWrapper<DictType> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,name,type,remark,status,create_time,update_time");
        queryWrapper.isNull("delete_time");
        queryWrapper.orderByDesc("id");

        List<DictType> dictTypeList = dictTypeMapper.selectList(queryWrapper);

        List<SettingDictTypeVo> list = new LinkedList<>();
        for (DictType dictType : dictTypeList) {
            SettingDictTypeVo vo = new SettingDictTypeVo();
            BeanUtils.copyProperties(dictType, vo);

            vo.setCreateTime(TimeUtils.timestampToDate(dictType.getCreateTime()));
            vo.setUpdateTime(TimeUtils.timestampToDate(dictType.getUpdateTime()));
            list.add(vo);
        }

        return list;
    }

    /**
     * 字典类型列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param params 搜索参数
     * @return PageResult<DictDataVo>
     */
    @Override
    public PageResult<SettingDictTypeVo> list(PageValidate pageValidate, Map<String, String> params) {
        Integer page  = pageValidate.getPage_no();
        Integer limit = pageValidate.getPage_size();

        QueryWrapper<DictType> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,name,type,remark,status,create_time,update_time");
        queryWrapper.isNull("delete_time");
        queryWrapper.orderByDesc("id");

        dictTypeMapper.setSearch(queryWrapper, params, new String[]{
                "like:name@name:str",
                "like:type@type:str",
                "=:status@status:int",
        });

        IPage<DictType> iPage = dictTypeMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SettingDictTypeVo> list = new LinkedList<>();
        for (DictType dictType : iPage.getRecords()) {
            SettingDictTypeVo vo = new SettingDictTypeVo();
            BeanUtils.copyProperties(dictType, vo);
            vo.setCreateTime(TimeUtils.timestampToDate(dictType.getCreateTime()));
            vo.setUpdateTime(TimeUtils.timestampToDate(dictType.getUpdateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 字典类型详情
     *
     * @author fzr
     * @param id 主键
     * @return DictDataVo
     */
    @Override
    public SettingDictTypeVo detail(Integer id) {
        DictType dictType = dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .select("id,name,type,remark,status,create_time,update_time")
                .eq("id", id)
                .isNull("delete_time")
                .last("limit 1"));

        Assert.notNull(dictType, "字典类型不存在！");

        SettingDictTypeVo vo = new SettingDictTypeVo();
        BeanUtils.copyProperties(dictType, vo);
        vo.setCreateTime(TimeUtils.timestampToDate(dictType.getCreateTime()));
        vo.setUpdateTime(TimeUtils.timestampToDate(dictType.getUpdateTime()));
        return vo;
    }

    /**
     * 字典类型新增
     *
     * @author fzr
     * @param createValidate 参数
     */
    @Override
    public void add(DictTypeCreateValidate createValidate) {
        Assert.isNull(dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .select("id")
                .eq("name", createValidate.getName())
                .isNull("delete_time")
                .last("limit 1")), "字典名称已存在！");

       Assert.isNull(dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .select("id")
                .eq("type", createValidate.getType())
                .isNull("delete_time")
                .last("limit 1")), "字典类型已存在！");

        DictType model = new DictType();
        model.setName(createValidate.getName());
        model.setType(createValidate.getType());
        model.setRemark(createValidate.getRemark());
        model.setStatus(createValidate.getStatus());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        dictTypeMapper.insert(model);
    }

    /**
     * 字典类型编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    @Override
    public void edit(DictTypeUpdateValidate updateValidate) {
        DictType model = dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                        .eq("id", updateValidate.getId())
                        .isNull("delete_time")
                        .last("limit 1"));

        Assert.notNull(model, "字典类型不存在！");

        Assert.isNull(dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .ne("id", updateValidate.getId())
                .eq("name", updateValidate.getName())
                .isNull("delete_time")
                .last("limit 1")), "字典类型已存在！");

        Assert.isNull(dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .ne("id", updateValidate.getId())
                .eq("type", updateValidate.getType())
                .isNull("delete_time")
                .last("limit 1")), "字典类型已存在！");

        model.setName(updateValidate.getName());
        model.setType(updateValidate.getType());
        model.setRemark(updateValidate.getRemark());
        model.setStatus(updateValidate.getStatus());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        dictTypeMapper.updateById(model);
    }

    /**
     * 字典类型删除
     *
     * @author fzr
     * @param id 主键
     */
    @Override
    public void del(Integer id) {
        DictType model = new DictType();
        model.setId(id);
        model.setDeleteTime(System.currentTimeMillis() / 1000);
        dictTypeMapper.updateById(model);
    }

}
