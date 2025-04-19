package cn.liaozh.admin_api.validate.setting;

import cn.liaozh.admin_api.vo.setting.SettingPaymentMethodVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "支付方式设置参数")
public class SettingPayMethodValidate {

    List<List<SettingPaymentMethodVo>> data;

}
