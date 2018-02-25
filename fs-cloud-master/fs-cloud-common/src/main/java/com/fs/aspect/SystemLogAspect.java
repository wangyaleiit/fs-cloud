package com.fs.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.fs.annotation.SystemControllerLog;
import com.fs.annotation.SystemServiceLog;
import com.fs.utils.DateUtils;

/***
 * Log处理切入点
 * @ClassName SystemLogAspect
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2017年11月20日
 */
@Aspect
@Component
public class SystemLogAspect {

	// 本地异常日志记录对象
	protected Logger logger = LoggerFactory.getLogger(getClass());

	// Service层切点
	@Pointcut("@annotation(com.fs.annotation.SystemServiceLog)")
	public void serviceAspect() {
	}

	// Controller层切点
	@Pointcut("@annotation(com.fs.annotation.SystemControllerLog)")
	public void controllerAspect() {
	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		try {
			// 取得用户信息
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			// 用户信息
			// 请求的IP
			String ip = request.getRemoteAddr();
			// 请求方法
			String methods = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()
					+ "()";
			// 方法描述
			String description = getControllerMethodDescription(joinPoint);
			// 触发时间
			String logData = DateUtils.getTime();
			// 插入数据库
			// Log格式
			logger.info("Start：" + ip + "_" + methods + "_" + description + "_" + logData);
		} catch (Exception e) {
			logger.error("==前置通知异常==");
			logger.error("异常信息:{}", e.getMessage());
		}
	}

	/***
	 * 异常通知 用于拦截service层记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		try {
			// 取得用户信息
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			// 用户信息
			// 请求的IP
			String ip = request.getRemoteAddr();
			// 获取用户请求方法的参数并序列化为JSON格式字符串
			String params = "";
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				for (int i = 0; i < joinPoint.getArgs().length; i++) {
					params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";
				}
			}
			logger.error("==异常通知开始==");
			// 请求方法
			String methods = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()";
			// 方法描述
			String description = getServiceMthodDescription(joinPoint);
			// 触发时间
			String logData = DateUtils.getTime();
			// 异常代码
			String errMsg = e.getClass().getName() + "：" + e.getMessage();
			// Log格式
			logger.info("Err_Start：" + errMsg + "_" + ip + "_" + methods + "_" + params + "_" + description + "_" + logData);
			// 插入DB
		} catch (Exception ex) {
			logger.error("==Service通知异常==");
			logger.error("异常信息:{}", ex.getMessage());
		}
	}

	/***
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemControllerLog.class).description();
					break;
				}
			}
		}
		return description;
	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 * 
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				@SuppressWarnings("rawtypes")
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemServiceLog.class).description();
					break;
				}
			}
		}
		return description;
	}
}
