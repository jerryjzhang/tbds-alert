package com.tencent.tbds.alert;

import com.tencent.tbds.alert.dto.Response;
import com.tencent.tbds.alert.dto.ResponseStatus;
import com.tencent.tbds.alert.exception.TBDSAlertException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by jerryjzhang on 2016/3/22.
 */
@Aspect
@Component
public class ControllerResponseEncoder {
  private static Logger LOG = LoggerFactory.getLogger(ControllerResponseEncoder.class);

  @Around("execution(* com.tencent.tbds.alert.controller.*Controller.*(..))")
  public Response wrapResultAndException(ProceedingJoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();

    Response response = new Response();

    try {
      Object result = joinPoint.proceed(args);
      response.setResultData(result);
      response.setResultCode(ResponseStatus.SUCCESS.getCode());
      response.setMessage(ResponseStatus.SUCCESS.getMsg());
    } catch(TBDSAlertException e) {
      response.setResultCode(e.getCode());
      response.setMessage(e.getMessage());

      StringWriter writer = new StringWriter();
      PrintWriter printer = new PrintWriter(writer);
      e.printStackTrace(printer);
      LOG.info(writer.toString());

      e.printStackTrace();
    } catch (Throwable e) {
      response.setResultCode(ResponseStatus.INTERNAL_ERROR.getCode());
      response.setMessage(ResponseStatus.INTERNAL_ERROR.getMsg());

      StringWriter writer = new StringWriter();
      PrintWriter printer = new PrintWriter(writer);
      e.printStackTrace(printer);
      LOG.info(writer.toString());

      e.printStackTrace();
    }

    return response;
  }
}
