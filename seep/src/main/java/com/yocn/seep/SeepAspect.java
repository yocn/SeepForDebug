package com.yocn.seep;

import android.view.MotionEvent;

import com.yocn.seep.net.NetGrabber;
import com.yocn.seep.net.bean.NetResult;
import com.yocn.seep.ui.util.SeepLogger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.DateTime;

import androidx.appcompat.app.AppCompatActivity;

@Aspect
public class SeepAspect {
    String TAG = "Seep";

    @Around("execution(* com.yocn.dumpanalysis.MainActivity.dispatchTouchEvent(..))")
    public Object dispatchTouchEvent(ProceedingJoinPoint joinPoint) throws Throwable {
        SeepLogger.d("yocn  sssssssss  dispatchTouchEvent");
        Object[] parameterValues = joinPoint.getArgs();
        MotionEvent motionEvent = (MotionEvent) parameterValues[0];
        if (Seep.handleTouchEvent((AppCompatActivity) joinPoint.getThis(), motionEvent)) {
            return true;
        }

        return joinPoint.proceed();
    }

    @Around("execution(* com.yocn.dumpanalysis.MainActivity.onBackPressed())")
    public Object onBackPressed(ProceedingJoinPoint joinPoint) throws Throwable {
//        if (Seep.handleBackPressed()) {
//            return true;
//        }
        SeepLogger.d("yocn  sssssssss");
        return joinPoint.proceed();
    }

    @Around("execution(* com.mxtech.videoplayer.ad.online.apiclient.APIUtil.get(..))")
    public Object get(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] parameterValues = joinPoint.getArgs();
        Object o = joinPoint.proceed();
        if (parameterValues != null && parameterValues.length > 0) {
            Object url = parameterValues[0];
            SeepLogger.dt(Seep.TAG, "get  url:  " + parameterValues[0]);
            NetGrabber.getInstance().grabNet(new NetResult(url.toString(), o, new DateTime()));
        } else {
            SeepLogger.dt(Seep.TAG, "get  null:" + o);
        }
        return o;
    }

    @Around("execution(* com.mxtech.videoplayer.ad.online.apiclient.APIUtil.post(..))")
    public Object post(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] parameterValues = joinPoint.getArgs();
        Object o = joinPoint.proceed();
        if (parameterValues != null && parameterValues.length > 0) {
            Object url = parameterValues[0];
            SeepLogger.dt(Seep.TAG, "post  url " + parameterValues[0]);
            NetGrabber.getInstance().grabNet(new NetResult(url.toString(), o, new DateTime()));
        } else {
            SeepLogger.dt(Seep.TAG, "post  null:" + o);
        }
        return o;
    }
}
