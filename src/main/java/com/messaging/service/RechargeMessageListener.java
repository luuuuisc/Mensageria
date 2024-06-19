package com.messaging.service;


import com.messaging.config.RabbitMQConfig;
import com.messaging.dto.RechargeDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RechargeMessageListener {

    @Autowired
    private RechargeService rechargeService; // Serviço que contém a lógica de negócios

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveMessage(RechargeDTO recharge) {
        try {
            System.out.println("Received recharge request for: " + recharge.getPhoneNumber());

            // Verificar se a recarga é válida
            if (recharge.getAmount() <= 0) {
                throw new IllegalArgumentException("Invalid recharge amount");
            }

            // Supondo que exista uma validação para o número de telefone
            if (!isValidPhoneNumber(recharge.getPhoneNumber())) {
                throw new IllegalArgumentException("Invalid phone number");
            }

            // Processar a recarga
            rechargeService.processRecharge(recharge);

            // Enviar confirmação de sucesso para a fila de resposta
            rechargeService.sendRechargeResponse(recharge, true, "");

        } catch (Exception e) {
            // Log de erro e envio de resposta de falha
            System.err.println("Error processing recharge: " + e.getMessage());
            rechargeService.sendRechargeResponse(recharge, false, e.getMessage());
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\(\\d{2}\\)9\\d{8}");
    }
}


