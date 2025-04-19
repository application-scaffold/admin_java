package cn.liaozh.admin_api.controller.channel;

import cn.liaozh.admin_api.service.IChannelOaCallBackService;
import cn.liaozh.common.aop.NotLogin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/adminapi/channel/oa/callback")
@Tag(name = "公众号服务器验证及消息回复")
public class ChannelOaCallBackController {

    @Resource
    private IChannelOaCallBackService iChannelOaCallBackService;

    @NotLogin
    @GetMapping(produces = "text/plain;charset=utf-8")
    @Operation(summary = "公众号服务器地址验证")
    public String authGet(@RequestParam(name = "signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr) {
        return iChannelOaCallBackService.checkSignature(signature, timestamp, nonce, echostr);
    }


    @NotLogin
    @PostMapping(produces = "application/xml; charset=UTF-8")
    @Operation(summary = "公众号消息回复")
    public String post(@RequestBody String requestBody,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature) {
        return iChannelOaCallBackService.post(requestBody, signature, timestamp, nonce, encType, msgSignature);
    }


}
