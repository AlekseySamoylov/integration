package com.alekseysamoylov.integration

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(BlogProperties::class)
@SpringBootApplication
class IntegrationApplication

fun main(args: Array<String>) {
  runApplication<IntegrationApplication>(*args) {
    setBannerMode(Banner.Mode.OFF)
  }
}
