package com.alekseysamoylov.integration.messaging

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component


@Component
class BroadcastMessageConsumers {

  @RabbitListener(queues = [QUEUE_NAME_IMPORTANT])
  fun receiveMessageFromImportantQueue(message: String) {
    println("Important message $message")
  }

  @RabbitListener(queues = [QUEUE_NAME_ERROR])
  fun receiveMessageFromErrorQueue(message: String) {
    println("Error message $message")
  }
}
