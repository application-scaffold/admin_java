package cn.liaozh.admin_api.service;

import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.vo.decorate.DecorateDataArticleVo;

import java.util.List;

/**
 * 装修数据服务接口类
 */
public interface IDecorateDataService {

    /**
     * 获取文章数据
     *
     * @author fzr
     * @param limit 条数
     * @return List<DecorateArticleDataVo>
     */
    List<DecorateDataArticleVo> article(Integer limit);

    JSONObject pc();
}
