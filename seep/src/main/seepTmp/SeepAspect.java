package com.mxtech.videoplayer.ad.seep;


import android.view.MotionEvent;

import com.mxplay.logger.ZenLogger;
import com.mxtech.videoplayer.ad.seep.net.NetGrabber;
import com.mxtech.videoplayer.ad.seep.net.bean.NetResult;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.DateTime;

import androidx.appcompat.app.AppCompatActivity;

@Aspect
public class SeepAspect {
    String TAG = "Seep";

    @Around("execution(* com.mxtech.app.MXAppCompatActivityMultiLanguageBase.dispatchTouchEvent(..))")
    public Object dispatchTouchEvent(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] parameterValues = joinPoint.getArgs();
        MotionEvent motionEvent = (MotionEvent) parameterValues[0];
        if (Seep.handleTouchEvent((AppCompatActivity) joinPoint.getThis(), motionEvent)) {
            return true;
        }

        return joinPoint.proceed();
    }


    @Around("execution(* com.mxtech.app.MXAppCompatActivityMultiLanguageBase.onBackPressed())")
    public Object onBackPressed(ProceedingJoinPoint joinPoint) throws Throwable {
        if (Seep.handleBackPressed()) {
            return true;
        }
        return joinPoint.proceed();
    }

    @Around("execution(* com.mxtech.videoplayer.ad.online.apiclient.APIUtil.get(..))")
    public Object get(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] parameterValues = joinPoint.getArgs();
        Object o = joinPoint.proceed();
        if (parameterValues != null && parameterValues.length > 0) {
            Object url = parameterValues[0];
            ZenLogger.dt(Seep.TAG, "get  url: %s ", parameterValues[0]);
            NetGrabber.getInstance().grabNet(new NetResult(url.toString(), o, new DateTime()));
        } else {
            ZenLogger.dt(Seep.TAG, "get  null:%s", o);
        }
        return o;
    }

    @Around("execution(* com.mxtech.videoplayer.ad.online.apiclient.APIUtil.post(..))")
    public Object post(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] parameterValues = joinPoint.getArgs();
        Object o = joinPoint.proceed();
        if (parameterValues != null && parameterValues.length > 0) {
            Object url = parameterValues[0];
            ZenLogger.dt(Seep.TAG, "post  url:%s ", parameterValues[0]);
            NetGrabber.getInstance().grabNet(new NetResult(url.toString(), o, new DateTime()));
        } else {
            ZenLogger.dt(Seep.TAG, "post  null:%s", o);
        }
        return o;
    }
}
