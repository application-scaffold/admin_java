package cn.liaozh.admin_api.service;

import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.user.UserSearchValidate;
import cn.liaozh.admin_api.validate.user.UserUpdateValidate;
import cn.liaozh.admin_api.validate.user.UserWalletValidate;
import cn.liaozh.admin_api.vo.user.UserVo;
import cn.liaozh.common.core.PageResult;

/**
 * 用户服务接口类
 */
public interface IUserService {

    /**
     * 用户列表
     *
     * @author fzr
     * @param pageValidate (分页参数)
     * @param searchValidate (搜索参数)
     * @return PageResult<UserVo>
     */
    PageResult<UserVo> list(PageValidate pageValidate, UserSearchValidate searchValidate);

    /**
     * 用户详情
     *
     * @author fzr
     * @param id 主键
     * @return UserVo
     */
    UserVo detail(Integer id);

    /**
     * 用户编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    void edit(UserUpdateValidate updateValidate);

    /**
     * 余额调整
     *
     * @author fzr
     * @param userWalletValidate 余额
     */
    void adjustWallet(UserWalletValidate userWalletValidate);

    /**
     * 返回导出格式
     * @return
     */
    JSONObject getExportData(PageValidate pageValidate, UserSearchValidate searchValidate);

    /**
     * 导出
     */
    String export(UserSearchValidate searchValidate);
}
