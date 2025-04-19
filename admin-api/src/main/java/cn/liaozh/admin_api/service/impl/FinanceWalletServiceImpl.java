package cn.liaozh.admin_api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import cn.liaozh.admin_api.service.IFinanceWalletService;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.finance.FinanceWalletSearchValidate;
import cn.liaozh.admin_api.vo.finance.FinanceWalletListVo;
import cn.liaozh.common.config.GlobalConfig;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.entity.log.UserAccountLog;
import cn.liaozh.common.enums.LogMoneyEnum;
import cn.liaozh.common.mapper.log.UserAccountLogMapper;
import cn.liaozh.common.util.StringUtils;
import cn.liaozh.common.util.TimeUtils;
import cn.liaozh.common.util.UrlUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户余额记录服务实现类
 */
@Service
public class FinanceWalletServiceImpl implements IFinanceWalletService {

    @Resource
    UserAccountLogMapper logMoneyMapper;

    @Override
    public PageResult<FinanceWalletListVo> list(PageValidate pageValidate, FinanceWalletSearchValidate searchValidate) {
        Integer pageNo = pageValidate.getPage_no();
        Integer pageSize = pageValidate.getPage_size();

        MPJQueryWrapper<UserAccountLog> mpjQueryWrapper = new MPJQueryWrapper<>();
        mpjQueryWrapper.selectAll(UserAccountLog.class)
                .select("U.id as user_id,U.sn as sn,U.nickname,U.avatar, U.account, U.mobile")
                .leftJoin("?_user U ON U.id=t.user_id".replace("?_", GlobalConfig.tablePrefix))
                .orderByDesc("id");

        logMoneyMapper.setSearch(mpjQueryWrapper, searchValidate, new String[]{
                "=:change_type@t.change_type:int",
                "datetime:start_time-end_time@t.create_time:str",
        });

        if (StringUtils.isNotEmpty(searchValidate.getUser_info())) {
            String keyword = searchValidate.getUser_info();
            mpjQueryWrapper.nested(wq->wq
                    .like("U.nickname", keyword).or()
                    .like("U.account", keyword).or()
                    .like("U.sn", keyword).or()
                    .like("U.mobile", keyword));
        }

        IPage<FinanceWalletListVo> iPage = logMoneyMapper.selectJoinPage(
                new Page<>(pageNo, pageSize),
                FinanceWalletListVo.class,
                mpjQueryWrapper);

        for (FinanceWalletListVo vo : iPage.getRecords()) {
            vo.setCreateTime(TimeUtils.timestampToDate(vo.getCreateTime()));
            vo.setChangeTypeDesc(LogMoneyEnum.getMsgByCode(Integer.parseInt(vo.getChangeType())));
            vo.setAvatar(UrlUtils.toAdminAbsoluteUrl(vo.getAvatar()));
        }

        Map<String, Object> extend = new LinkedHashMap<>();
        extend.put("changeType", LogMoneyEnum.getTypeList());

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), iPage.getRecords(), extend);
    }

    @Override
    public Map<Integer, String> getUmChangeType() {
        return LogMoneyEnum.getTypeList();
    }
}
