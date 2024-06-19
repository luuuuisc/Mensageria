package com.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "rechargeQueue";
    public static final String EXCHANGE = "rechargeExchange";
    public static final String ROUTING_KEY = "recharge";

    public static final String RESPONSE_QUEUE = "responseQueue";
    public static final String EXCHANGE_RESPONSE = "responseExchange";;
    public static final String ROUTING_KEY_RESPONSE = "response";

    @Bean
    Queue queue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    // Configuração para enviar respostas
    @Bean
    Queue responseQueue() {
        return new Queue(RESPONSE_QUEUE, true);
    }

    @Bean
    DirectExchange responseExchange() {
        return new DirectExchange(EXCHANGE_RESPONSE);
    }

    @Bean
    Binding responseBinding(Queue responseQueue, DirectExchange responseExchange) {
        return BindingBuilder.bind(responseQueue).to(responseExchange).with(ROUTING_KEY_RESPONSE);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
