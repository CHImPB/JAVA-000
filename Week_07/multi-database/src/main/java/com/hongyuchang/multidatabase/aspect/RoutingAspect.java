package com.hongyuchang.multidatabase.aspect;

import com.hongyuchang.multidatabase.annotation.RoutingWith;
import com.hongyuchang.multidatabase.config.RoutingDataSourceConfig;
import com.hongyuchang.multidatabase.constant.DataSourceConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Administrator
 */
@Aspect
@Component
public class RoutingAspect {
    @Pointcut("@annotation(com.hongyuchang.multidatabase.annotation.RoutingWith)")
    public void routingWith(){}

    @Around("routingWith() && @annotation(routingWith)")
    public Object routingWithDataSource(ProceedingJoinPoint joinPoint, RoutingWith routingWith) throws Throwable {
        if(routingWith.readOnly()){
            final String slave = loadBalance();
            RoutingDataSourceConfig.setDataSource(slave);
        }
        else {
            RoutingDataSourceConfig.setDataSource(DataSourceConstant.MASTER);
        }
        return joinPoint.proceed();
    }

    private String loadBalance(){
        Random random = new Random();
        final int i = random.nextInt(2) + 1;
        switch (i){
            case 1:
                return DataSourceConstant.SLAVE;
            case 2:
                return DataSourceConstant.SLAVE2;
        }
        return DataSourceConstant.SLAVE;
    }
}
