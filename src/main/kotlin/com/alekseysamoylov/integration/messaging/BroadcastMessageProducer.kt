package com.alekseysamoylov.integration.messaging

import com.alekseysamoylov.integration.config.TOPIC_TO_EXCHANGE
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class BroadcastMessageProducer(
    private val rabbitTemplate: RabbitTemplate
) {
  fun sendMessages(message: String) {
    rabbitTemplate.convertAndSend(
        TOPIC_TO_EXCHANGE, "user.important.info", "important $message")
    rabbitTemplate.convertAndSend(
        TOPIC_TO_EXCHANGE, "user.error.main", "error $message")
  }
}
