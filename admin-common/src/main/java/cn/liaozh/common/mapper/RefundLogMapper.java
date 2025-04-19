package cn.liaozh.common.mapper;

import cn.liaozh.common.core.basics.IBaseMapper;
import cn.liaozh.common.entity.RefundLog;
import cn.liaozh.common.util.TimeUtils;
import cn.liaozh.common.util.ToolUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退款日志Mapper
 */
@Mapper
public interface RefundLogMapper extends IBaseMapper<RefundLog> {

    /**
     * 生成唯一单号
     *
     * @author fzr
     * @param field 字段名
     * @return String
     */
    default String randMakeOrderSn(String field) {
        String date = TimeUtils.timestampToDate(System.currentTimeMillis()/1000, "yyyyMMddHHmmss");
        String sn;
        while (true) {
            sn = date + ToolUtils.randomInt(12);
            RefundLog snModel = this.selectOne(
                    new QueryWrapper<RefundLog>()
                            .select("id")
                            .eq(field, sn)
                            .last("limit 1"));
            if (snModel == null) {
                break;
            }
        }
        return sn;
    }

}
