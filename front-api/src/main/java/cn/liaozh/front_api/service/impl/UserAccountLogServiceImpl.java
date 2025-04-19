package cn.liaozh.front_api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.entity.log.UserAccountLog;
import cn.liaozh.common.enums.AccountLogEnum;
import cn.liaozh.common.mapper.log.UserAccountLogMapper;
import cn.liaozh.common.util.*;
import cn.liaozh.front_api.service.IUserAccountLogService;
import cn.liaozh.front_api.validate.common.PageValidate;
import cn.liaozh.front_api.validate.users.*;
import cn.liaozh.front_api.vo.user.UserAccountListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 用户服务实现类
 */
@Service
public class UserAccountLogServiceImpl implements IUserAccountLogService {

    @Resource
    UserAccountLogMapper userAccountLogMapper;

    @Override
    public PageResult<UserAccountListVo> lists(PageValidate pageValidate, UserAccountLogSearchValidate searchValidate) {
        Integer pageNo   = pageValidate.getPage_no();
        Integer pageSize = pageValidate.getPage_size();

        QueryWrapper<UserAccountLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("delete_time");
        queryWrapper.eq("user_id", searchValidate.getUserId());
        if (StringUtils.isNotEmpty(searchValidate.getType()) && searchValidate.getType().equals("um")) {
            queryWrapper.in("change_type", AccountLogEnum.getUserMoneyChangeType());
        }

        if (StringUtils.isNotNull(searchValidate.getAction())) {
            queryWrapper.eq("action", searchValidate.getAction());
        }

        queryWrapper.orderByDesc("id");
        IPage<UserAccountLog> iPage = userAccountLogMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);
        List<UserAccountListVo> list = new LinkedList<>();
        for (UserAccountLog item : iPage.getRecords()) {
            UserAccountListVo vo = new UserAccountListVo();
            BeanUtils.copyProperties(item, vo);
            vo.setTypeDesc(AccountLogEnum.getChangeTypeDesc(item.getChangeType()));
            String symbol = item.getAction().equals(AccountLogEnum.DEC.getCode()) ? "-" : "+";
            vo.setChangeAmountDesc(symbol + item.getChangeAmount());
            vo.setCreateTime(TimeUtils.timestampToDate(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }
}
