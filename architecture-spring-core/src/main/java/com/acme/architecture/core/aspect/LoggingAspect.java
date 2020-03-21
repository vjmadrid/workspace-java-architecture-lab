package com.acme.architecture.core.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.acme.architecture.core.config.PackageConfig;
import com.acme.architecture.core.constant.PackageConfigConstant;

@Aspect
@Component
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Pointcut("within(*) && execution(* " + PackageConfigConstant.COMMON_PACKAGE + "..*.controller.*.*(..))")
	public void inControllerLayer() {
		// Point of cut for controller layer logs
	}

	@Before("inControllerLayer()")
	public void loggerBeforeControllerLayer(JoinPoint jp) {
		String message = "Entegring controller layer. Method " + PackageConfig.getControllerPackage() + ".{} : {}";
		logger.debug(message, getMethodName(jp), getArguments(jp));
	}

	@AfterReturning(pointcut = "inControllerLayer()", returning = "result")
	public void loggerAfterControllerLayer(JoinPoint jp, Object result) {
		String message = "Exiting controller layer. Method " + PackageConfig.getControllerPackage() + ".{} : Return {}";
		logger.debug(message, getMethodName(jp), result);
	}

	@Pointcut("within(*) && execution(* " + PackageConfigConstant.COMMON_PACKAGE + "..*.service..*(..))")
	public void inServiceLayer() {
		// Point of cut for service layer logs
	}

	@Before("inServiceLayer()")
	public void loggerBeforeServiceLayer(JoinPoint jp) {
		String message = "Entegring service layer. Method " + PackageConfig.getServicePackage() + ".{} : {}";
		logger.debug(message, getMethodName(jp), getArguments(jp));
	}

	@AfterReturning(pointcut = "inServiceLayer()", returning = "result")
	public void loggerAfterServiceLayer(JoinPoint jp, Object result) {
		String message = "Exiting service layer. Method " + PackageConfig.getServicePackage() + ".{} : Return {}";
		logger.debug(message, getMethodName(jp), result);
	}

	protected String getMethodName(JoinPoint jp) {
		return StringUtils.capitalize(jp.getSignature().getName());
	}

	protected String getArguments(JoinPoint jp) {
		return (jp.getArgs().length == 0) ? "Without arguments" : "Args: " + jp.getArgs();
	}

}
