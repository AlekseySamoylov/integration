package com.alekseysamoylov.integration.messaging


import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Declarable
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*


@Configuration
class MessagingConfig {

  @Bean
  fun exchange(): TopicExchange {
    return TopicExchange(TOPIC_TO_EXCHANGE)
  }

  @Bean
  fun fanoutBindings(exchange: TopicExchange): List<Declarable> {
    val userQueue = Queue(QUEUE_NAME, false)
    val importantUserQueue = Queue(QUEUE_NAME_IMPORTANT, false)
    val errorUserQueue = Queue(QUEUE_NAME_ERROR, false)

    val userBinding = BindingBuilder.bind(userQueue).to(exchange).with("*")
    val errorBinding = BindingBuilder.bind(importantUserQueue).to(exchange).with("user.important.*")
    val userImportantBinding = BindingBuilder.bind(errorUserQueue).to(exchange).with("user.error.*")
    return Arrays.asList(
        userBinding,
        userImportantBinding,
        errorBinding
    )
  }
}


const val TOPIC_TO_EXCHANGE = "user-exchange"
const val QUEUE_NAME = "user"
const val QUEUE_NAME_IMPORTANT = "user-important"
const val QUEUE_NAME_ERROR = "user-error"
