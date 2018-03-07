package com.fs.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 微信消息认证及转发
 * @ClassName WechatAuthController
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2017年11月20日
 */
@Api(value="微信认证接口") 
@RestController
@RequestMapping("/wechat/auth")
public class WxAuthController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WxMpService wxService;

	@Autowired
	private WxMpMessageRouter router;

	/***
	 * 微信认证
	 * @param signature
	 * @param nonce
	 * @param timestamp
	 * @param echostr
	 * @return
	 */
	@ApiOperation(value="微信服务器的认证", notes="微信公众平台回调验证,非开发人员调用")
	@ApiImplicitParams({
		@ApiImplicitParam(name="signature",value="",required = true,dataType="String"),
		@ApiImplicitParam(name="nonce",value="",required = true,dataType="String"),
		@ApiImplicitParam(name="timestamp",value="",required = true,dataType="String"),
		@ApiImplicitParam(name="echostr",value="",required = true,dataType="String")
	})
	@GetMapping(value = "authApi", produces = "application/json;charset=UTF-8")
	public String checkSignature(@RequestParam(name = "signature", required = false) String signature,
			@RequestParam(name = "nonce", required = false) String nonce,
			@RequestParam(name = "timestamp", required = false) String timestamp,
			@RequestParam(name = "echostr", required = false) String echostr) {
		try {
			//this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);
			if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
				throw new IllegalArgumentException("请求参数非法，请核实!");
			}
			if (this.wxService.checkSignature(timestamp, nonce, signature)) {
				return echostr;
			}
		} catch (Exception e) {
			logger.error("接口验证失败: {}", e.getMessage());
			logger.error("异常详细信息 :", e);
		}
		return "非法请求";
	}

	/***
	 * 微信消息处理
	 * @param requestBody
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param encType
	 * @param msgSignature
	 * @return
	 */
	@ApiOperation(value="微信消息.事件路由处理", notes="微信主动挂起，非开发人员调用")
	@PostMapping(value = "authApi", produces = "application/xml;charset=UTF-8")
	public String acceptMessage(@RequestBody String requestBody, @RequestParam("signature") String signature,
			@RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce,
			@RequestParam(name = "encrypt_type", required = false) String encType,
			@RequestParam(name = "msg_signature", required = false) String msgSignature) {
		String out = null;
		try {
			this.logger.info(
					"\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
							+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
					signature, encType, msgSignature, timestamp, nonce, requestBody);
			if (!this.wxService.checkSignature(timestamp, nonce, signature)) {
				throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
			}
			if (StringUtils.isEmpty(encType)) {
				// 明文传输的消息
				WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
				WxMpXmlOutMessage outMessage = this.route(inMessage);
				if (outMessage == null) {
					return "";
				}
				out = outMessage.toXml();
			} else {
				// AES加密的消息
				WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody,
						this.wxService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
				this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
				WxMpXmlOutMessage outMessage = this.route(inMessage);
				if (outMessage == null) {
					return "";
				}
				out = outMessage.toEncryptedXml(this.wxService.getWxMpConfigStorage());
			}

		} catch (Exception e) {
			logger.error("接口验证失败: {}", e.getMessage());
			logger.error("异常详细信息 :", e);
		}
	    this.logger.debug("\n组装回复信息：{}", out);
	    return out;
	}

	/***
	 * 消息路由处理
	 * @param message
	 * @return
	 */
	private WxMpXmlOutMessage route(WxMpXmlMessage message) {
		try {
			return this.router.route(message);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return null;
	}
}
