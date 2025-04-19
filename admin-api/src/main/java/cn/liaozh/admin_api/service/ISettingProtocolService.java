package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.setting.SettingAgreementValidate;
import cn.liaozh.admin_api.vo.setting.SettingAgreementVo;

/**
 * 政策协议服务接口类
 */
public interface ISettingProtocolService {

    /**
     * 获取政策协议信息
     *
     * @author fzr
     * @return SettingProtocolDetailVo
     */
    SettingAgreementVo getAgreement();

    /**
     * 保存政策协议信息
     *
     * @author fzr
     * @param protocolValidate 参数
     */
    void setAgreement(SettingAgreementValidate protocolValidate);

}
