package cn.liaozh.common.mapper.log;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.liaozh.common.core.basics.IBaseMapper;
import cn.liaozh.common.entity.log.UserAccountLog;
import cn.liaozh.common.entity.user.User;
import cn.liaozh.common.enums.AccountLogEnum;
import cn.liaozh.common.mapper.user.UserMapper;
import cn.liaozh.common.util.SpringUtils;
import cn.liaozh.common.util.StringUtils;
import cn.liaozh.common.util.TimeUtils;
import cn.liaozh.common.util.ToolUtils;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

/**
 * 账户变动Mapper
 */
@Mapper
public interface UserAccountLogMapper extends IBaseMapper<UserAccountLog> {

    /**
     * 增加
     *
     * @param userId 用户ID
     * @param changeType 变动类型
     * @param changeAmount 变动金额
     * @param sourceId 来源ID
     * @param sourceSn 来源订单
     * @param remark 备注信息
     * @param extra 预留字段
     */
    default void add(Integer userId, Integer changeType, BigDecimal changeAmount,
                     Integer sourceId, String sourceSn, String remark, String extra) {

        for (int i=0; i<=0; i++) {
            UserMapper userMapper = SpringUtils.getBean(UserMapper.class);
            User user = userMapper.selectById(userId);
            if (StringUtils.isNull(user)) {
                break;
            }

            Integer changeObject = AccountLogEnum.getChangeObject(changeType);

            BigDecimal leftAmount = user.getUserMoney().add(changeAmount);
            UserAccountLog logMoney = new UserAccountLog();
            logMoney.setSn(this.randMakeOrderSn());
            logMoney.setUserId(userId);
            logMoney.setSourceSn(sourceSn);
            logMoney.setChangeObject(changeObject);
            logMoney.setChangeType(changeType);
            logMoney.setChangeAmount(changeAmount);
            logMoney.setLeftAmount(leftAmount);
            logMoney.setAction(1);
            logMoney.setRemark(remark);
            logMoney.setExtra(extra);
            logMoney.setCreateTime(System.currentTimeMillis() / 1000);
            logMoney.setUpdateTime(System.currentTimeMillis() / 1000);
            this.insert(logMoney);
        }
    }

    /**
     * 减少
     *
     * @param userId 用户ID
     * @param changeType 变动类型
     * @param changeAmount 变动金额
     * @param sourceId 来源ID
     * @param sourceSn 来源订单
     * @param remark 备注信息
     * @param extra 预留字段
     */
    default void dec(Integer userId, Integer changeType, BigDecimal changeAmount,
                     Integer sourceId, String sourceSn, String remark, String extra) {

        for (int i=0; i<=0; i++) {
            UserMapper userMapper = SpringUtils.getBean(UserMapper.class);
            User user = userMapper.selectById(userId);
            if (StringUtils.isNull(user)) {
                break;
            }

            BigDecimal leftAmount = user.getUserMoney().subtract(changeAmount);

            UserAccountLog logMoney = new UserAccountLog();
            logMoney.setSn(this.randMakeOrderSn());
            logMoney.setUserId(userId);
            logMoney.setSourceSn(sourceSn);
            logMoney.setChangeType(changeType);
            logMoney.setChangeAmount(changeAmount);
            logMoney.setLeftAmount(leftAmount);
            logMoney.setAction(2);
            logMoney.setRemark(remark);
            logMoney.setExtra(extra);
            logMoney.setCreateTime(System.currentTimeMillis() / 1000);
            logMoney.setUpdateTime(System.currentTimeMillis() / 1000);
            this.insert(logMoney);
        }
    }

    /**
     * 生成唯一流水号
     *
     * @author fzr
     * @return String
     */
    default String randMakeOrderSn() {
        String date = TimeUtils.timestampToDate(System.currentTimeMillis()/1000, "yyyyMMddHHmmss");
        String sn;
        while (true) {
            sn = date + ToolUtils.randomInt(12);
            UserAccountLog snModel = this.selectOne(
                    new QueryWrapper<UserAccountLog>()
                            .select("id")
                            .eq("sn", sn)
                            .last("limit 1"));
            if (snModel == null) {
                break;
            }
        }
        return sn;
    }

}
