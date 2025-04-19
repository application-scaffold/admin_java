package cn.liaozh.admin_api.service;

import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.vo.setting.SettingNoticeDetailVo;
import cn.liaozh.admin_api.vo.setting.SettingNoticeListedVo;

import java.util.List;

/**
 * 通知设置服务接口类
 */
public interface ISettingNoticeService {

    /**
     * 通知设置列表
     *
     * @author fzr
     * @param recipient 1=用户, 2=平台
     * @return List<NoticeSettingListVo>
     */
    List<SettingNoticeListedVo> list(Integer recipient);

    /**
     * 通知设置详情
     *
     * @author fzr
     * @param id 主键
     * @return NoticeDetailVo
     */
    SettingNoticeDetailVo detail(Integer id);

    /**
     * 通知设置保存
     *
     * @author fzr
     * @param params 参数
     */
    void save(JSONObject params);

}
