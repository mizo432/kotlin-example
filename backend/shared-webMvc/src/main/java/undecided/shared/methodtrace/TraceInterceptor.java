package undecided.shared.methodtrace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceInterceptor {
    @Around("execution(*Undecided *..*QueryImpl.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        final Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        log.info("メソッド開始: " + joinPoint.getSignature());
        try {
            //実行
            Object result = joinPoint.proceed(joinPoint.getArgs());
            log.info("メソッド終了: " + joinPoint.getSignature() + " 戻り値: " + result);
            return result;
        } catch (Exception ex) {
            log.error("メソッド異常終了: " + joinPoint.getSignature(), ex);
            throw ex;
        }
    }

}
