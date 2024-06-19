package com.messaging.service;

import com.messaging.dto.RechargeDTO;
import com.messaging.dto.RechargeResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.messaging.config.RabbitMQConfig;



@Service
public class RechargeService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void processRecharge(RechargeDTO recharge) {
        // Lógica para atualizar o banco de dados ou comunicar-se com outros sistemas
        System.out.println("Processing recharge for: " + recharge.getPhoneNumber());

        // Suponha que esta seja a atualização de saldo ou algo similar
        // repository.updateBalance(recharge.getPhoneNumber(), recharge.getAmount());
        System.out.println("Recharge processed successfully for " + recharge.getPhoneNumber());
    }

    public void sendRechargeResponse(RechargeDTO recharge, boolean success, String errorMessage) {
        RechargeResponse response = new RechargeResponse(recharge.getPhoneNumber(), success, errorMessage);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_RESPONSE, RabbitMQConfig.ROUTING_KEY_RESPONSE, response);
        System.out.println("Response sent back to the queue.");
    }
}


