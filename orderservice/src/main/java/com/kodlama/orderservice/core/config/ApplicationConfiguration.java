package com.kodlama.orderservice.core.config;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    Pipeline pipeline(
            ObjectProvider<Command.Handler> commandHandlers,
            ObjectProvider<Command.Middleware> middlewares)
    {
        return new Pipelinr()
                .with(commandHandlers::stream)
                .with(middlewares::orderedStream);
    }
}
