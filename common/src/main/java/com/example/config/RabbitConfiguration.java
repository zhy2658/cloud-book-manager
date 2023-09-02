package com.example.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    //交换机bean，可以很多个
    @Bean("directExchange")
    public Exchange exchange(){
        return ExchangeBuilder.directExchange("amq.direct").build();
    }

//    定义消息队列
    @Bean("yydsQueue")
    public Queue queue(){
        return QueueBuilder
                .nonDurable("login")    //非持久化类型
                .build();
    }

    @Bean("binding")
    public Binding binding(@Qualifier("directExchange") Exchange exchange,
                           @Qualifier("yydsQueue")  Queue queue
                           ){
        //将刚刚定义的交换机和队列进行绑定
        return BindingBuilder
                .bind(queue)    //绑定队列
                .to(exchange)   //到交换机
                .with("my-yyds")    //使用自定义的routineKey
                .noargs();

    }

    //直接创建一个用于json转换的bean
    @Bean("jackson1")
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }



}
