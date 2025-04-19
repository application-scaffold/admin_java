package cn.liaozh.admin_api.service.impl;

import com.alibaba.fastjson2.JSON;
import cn.liaozh.admin_api.service.ISettingCopyrightService;
import cn.liaozh.admin_api.validate.setting.SettingCopyrightValidate;
import cn.liaozh.admin_api.vo.setting.SettingCopyrightVo;
import cn.liaozh.common.util.ConfigUtils;
import cn.liaozh.common.util.ListUtils;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 网站备案服务实现类
 */
@Service
public class SettingCopyrightServiceImpl implements ISettingCopyrightService {

    /**
     * 获取网站备案信息
     *
     * @author fzr
     * @return List<SettingCopyrightVo>
     */
    @Override
    public List<SettingCopyrightVo> getCopyright() {
        String config = ConfigUtils.get("copyright", "config", "[]");
        List<Map<String, String>> copyright = ListUtils.stringToListAsMapStr(config);

        List<SettingCopyrightVo> list = new LinkedList<>();
        for (Map<String, String> map : copyright) {
            SettingCopyrightVo vo = new SettingCopyrightVo();
            vo.setKey(map.getOrDefault("key", ""));
            vo.setValue(map.getOrDefault("value", ""));
            list.add(vo);
        }

        return list;
    }

    /**
     * 保存网站备案信息
     *
     * @author fzr
     * @param copyrightValidate 参数
     */
    @Override
    public void setCopyright(SettingCopyrightValidate copyrightValidate) {
        String s = JSON.toJSONString(copyrightValidate.getConfig());
        ConfigUtils.set("copyright", "config", s);
    }

}
