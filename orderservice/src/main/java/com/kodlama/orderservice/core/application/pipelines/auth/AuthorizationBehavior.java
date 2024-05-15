package com.kodlama.orderservice.core.application.pipelines.auth;

import an.awesome.pipelinr.Command;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AuthorizationBehavior implements Command.Middleware
{

    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {
        if(c instanceof AuthCommand)
        {
            System.out.println("Middleware devreye girdi.");
        }
        return next.invoke();
    }
}
