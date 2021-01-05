package cn.tedu.straw.api.question.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimerAspect {

    @Around("execution(* cn.tedu.straw.api.question.service.impl.*.*(..))")
    public Object aaaaa(ProceedingJoinPoint pjp) throws Throwable {
        // 获取起始时间
        long start = System.currentTimeMillis();
        // 调用业务方法
        Object object = pjp.proceed();
        // 获取结束时间
        long end = System.currentTimeMillis();
        // 计算时间差
        System.out.println("执行耗时：" + (end - start) + "ms。");
        // 返回调用业务方法后得到的结果
        return object;
    }

}