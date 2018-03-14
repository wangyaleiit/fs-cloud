package com.fs.web;

import com.fs.entity.MiniEmployer;
import com.fs.service.MiniEmployerService;
import com.fs.utils.DateUtils;
import com.fs.utils.OrderUtil;
import com.fs.utils.PayConstant.PayStatus;
import com.fs.utils.PayConstant.SingleStatus;
import com.fs.utils.R;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayBaseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.common.collect.Lists;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.exception.WxErrorException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 支付处理
 * @ClassName WxPayController
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月17日
 */
@Api(tags={"微信支付管理"})
@RestController
@RequestMapping("/miniApp/pay")
public class WxPayController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "wxPayService")
	private WxPayService wxMpPayService;
	
	@Resource(name = "wxMaService")
	private WxMaService wxMaService;
	
    @Autowired
    private MiniEmployerService employerService;
    
    @Value("wechat.miniapp.notifyURL")
    private String notifyURL;
    

	/***
	 * 微信支付
	 * @param orderRequest
	 * @param request
	 * @return
	 */
	@ApiOperation("微信支付")
	@PostMapping(value = "createOrder/{id}")
	public R createOrder(WxPayUnifiedOrderRequest orderRequest,HttpServletRequest request,
			@PathVariable String id) {
		try {
		    orderRequest.setOutTradeNo(OrderUtil.GetOrderNumber("mini"));
		    //orderRequest.setTotalFee(WxPayBaseRequest.yuanToFee(String.valueOf(orderRequest.getTotalFee())));
		    // TODO
		    orderRequest.setTotalFee(WxPayBaseRequest.yuanToFee("0.01"));
		    orderRequest.setSpbillCreateIp(request.getRemoteAddr());
		    orderRequest.setAttach(id);
		    orderRequest.setNotifyURL(notifyURL);
		    orderRequest.setTradeType("JSAPI");
		    MiniEmployer employer = new MiniEmployer();
		    employer.setPayStatus(PayStatus.PAYING.toString());
		    employer.setId(id);
		    if(employerService.update(employer) > 0){
		    	return R.ok("payInfo",wxMpPayService.createOrder(orderRequest));
		    } else{
		    	return R.error("支付失败，请稍后重试！");
		    }
		} catch (WxPayException e) {
			logger.error("微信支付失败！订单号：{},原因:{}", e.getMessage());
			e.printStackTrace();
			return R.error("支付失败，请稍后重试！");
		}
	}
	
	/***
	 * 微信支付回调
	 */
	@RequestMapping("/notify")
	public String notifyRes(HttpServletRequest request, HttpServletResponse response){
		String xmlResult;
		try {
			xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		    WxPayOrderNotifyResult result = wxMpPayService.parseOrderNotifyResult(xmlResult);
		    MiniEmployer employer = new MiniEmployer();
		    employer.setPayStatus(PayStatus.PAID.toString());
		    employer.setId(result.getAttach());
		    employer.setTradeNo(result.getTransactionId());
		    if(employerService.update(employer) > 0){
		    	 return WxPayNotifyResponse.success("处理成功!");
		    } else{
		    	return WxPayNotifyResponse.fail("系统错误，请与商家联系");
		    }
		} catch (Exception e) {
			logger.error("微信回调结果异常,异常原因{}", e.getMessage());
			return WxPayNotifyResponse.fail(e.getMessage());
		}
	}
	
	/***
	 * 发送模板消息
	 * @return
	 */
	@PostMapping("/sendMsg/{prepayId}/{fromIds}")
	public R sendTemplateMsg(@PathVariable String prepayId,@PathVariable List<String> fromIds,@RequestBody MiniEmployer employer){
	   	 try {
	   		String formId = prepayId.replace("prepay_id=", "");
			wxMaService.getMsgService().sendTemplateMsg(WxMaTemplateMessage.builder()
				 .templateId("rSka9umUDsUzhifY6zStKcuDfPRKFDRAUYFuPggI5JA")
				 .formId(formId)
				 .data(Lists.newArrayList(
						 new WxMaTemplateMessage.Data("keyword1", "家政服务", "#4a4a4a"),
						 new WxMaTemplateMessage.Data("keyword2", employer.getStartDate(), "#9b9b9b"),
						 new WxMaTemplateMessage.Data("keyword3", String.valueOf(employer.getTypeMoney()), "#9b9b9b"),
						 new WxMaTemplateMessage.Data("keyword4", DateUtils.getTime(), "#9b9b9b"),
						 new WxMaTemplateMessage.Data("keyword5", employer.getChooseAres() +  "-" + employer.getChooseAddress() +  "-" + employer.getAddress(), "#173177")
			  )).toUser(employer.getOpenId()).build());
			// boss发送模板消息
			
		} catch (WxErrorException e) {
			logger.error("发送模板消息结果异常,异常原因{}", e.getMessage());
			return R.error();
		}
		return R.ok();
	}
	
	
	/***
	 * 微信退款
	 * @return
	 */
	@PostMapping("/refund/{id}")
	public R refund(WxPayRefundRequest request,@PathVariable String id){
    	MiniEmployer employer = employerService.findById(id);
	    try {
	    	request.setOutRefundNo(employer.getTradeNo());
	    	request.setTransactionId(employer.getTradeNo());
    		request.setTotalFee(WxPayBaseRequest.yuanToFee(String.valueOf(employer.getTypeMoney())));
	    	request.setOpUserId(employer.getOpenId());
	    	if(SingleStatus.UNSINGlE.equals(employer.getFlag())){
	    		request.setRefundFee(WxPayBaseRequest.yuanToFee(String.valueOf(employer.getTypeMoney())));
	    	} else {
	    		long createTime = employer.getCreateTime().getTime();
	    		long serviceTime =DateUtils.fomatDate("yyyy年MM月dd日 HH:mm", employer.getStartDate()).getTime();
	    		long nowTime = Calendar.getInstance().getTimeInMillis();
	    		long createDffTime = DateUtils.diffMinute(createTime,nowTime,false);
	    		long serviceDffTime = DateUtils.diffMinute(serviceTime,nowTime,false);
	    		if(createDffTime <= 30){
		    		request.setRefundFee(WxPayBaseRequest.yuanToFee(String.valueOf(employer.getTypeMoney())));
	    		} else{
	    			Double surplus = 0d;
	    			if(serviceDffTime > 30){
	    				surplus = employer.getTypeMoney() - employer.getTypeMoney() * 0.15;
	    			}else if(serviceDffTime > 0){
	    				surplus = employer.getTypeMoney() - employer.getTypeMoney() * 0.3;
	    			} else {
	    				// TODO
	    				surplus = employer.getTypeMoney() - employer.getTypeMoney() * 0.3;
	    			}
	    			request.setRefundFee(WxPayBaseRequest.yuanToFee(String.valueOf(surplus)));
	    		}
	    	}
	    	employer.setPayStatus(PayStatus.REFUND.toString());
	    	employer.setRefundTime(DateUtils.getTime());
	    	if(employerService.update(employer) > 0){
				return R.ok("payInfo",wxMpPayService.refund(request));
	    	} else {
				return R.error("退款失败，请稍后重试！");
	    	}
		} catch (Exception e) {
		 	employer.setPayStatus(PayStatus.PAYING.toString());
			employerService.update(employer);
			logger.error("微信退款失败！订单号：{},原因:{}", e.getMessage());
			e.printStackTrace();
			return R.error("退款失败，请稍后重试！");
		}
	}
	
}
