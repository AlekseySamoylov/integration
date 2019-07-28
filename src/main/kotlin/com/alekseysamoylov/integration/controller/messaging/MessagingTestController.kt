package com.alekseysamoylov.integration.controller.messaging

import com.alekseysamoylov.integration.controller.API_URI
import com.alekseysamoylov.integration.messaging.BroadcastMessageProducer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$API_URI/messaging")
class MessagingTestController(
    private val broadcastMessageProducer: BroadcastMessageProducer
) {

  @GetMapping("/test")
  fun test() {
    broadcastMessageProducer.sendMessages("Hello World")
  }
}
