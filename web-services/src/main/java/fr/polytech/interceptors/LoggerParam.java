package fr.polytech.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

public class LoggerParam implements Serializable {
    @AroundInvoke
    public Object methodLogger(InvocationContext ctx) throws Exception {
        String id = ctx.getTarget().getClass().getSimpleName() + "::" + ctx.getMethod().getName();
        System.out.println("*** Logger intercepts " + id);
        try {
            return ctx.proceed();
        } finally {
            System.out.println("*** End of interception for " + id);
        }
    }
}
