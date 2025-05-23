package cn.liaozh.admin_api.service;

import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.validate.setting.SettingPayConfigValidate;
import cn.liaozh.admin_api.vo.setting.SettingPaymentMethodVo;
import cn.liaozh.common.entity.setting.DevPayConfig;

import java.util.HashMap;
import java.util.List;

/**
 * 支付配置服务接口类
 */
public interface ISettingPaymentService {

    /**
     * 支付方式
     *
     * @author fzr
     * @return List<List<SettingPaymentMethodVo>>
     */
    HashMap getPayWay();

    /**
     * 配置列表
     *
     * @author fzr
     * @return List<DevPayConfig>
     */
    JSONObject list();

    /**
     * 配置详情
     *
     * @author fzr
     * @param id 主键
     * @return SettingPaymentMethodVo
     */
    DevPayConfig getConfig(Integer id);

    /**
     * 编辑支付配置
     *
     * @author fzr
     * @param configValidate 参数
     */
    void setConfig(SettingPayConfigValidate configValidate);

    /**
     * 编辑支付方式
     *
     * @author fzr
     * @param data 参数
     */
    void setPayWay(HashMap<Integer, List<SettingPaymentMethodVo>> data);

}
