package com.alekseysamoylov.integration

import com.alekseysamoylov.integration.rest.BlogProperties
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.hystrix.EnableHystrix

@EnableConfigurationProperties(BlogProperties::class)
@EnableHystrix
@SpringBootApplication
class IntegrationApplication

fun main(args: Array<String>) {
  runApplication<IntegrationApplication>(*args) {
    setBannerMode(Banner.Mode.OFF)
  }
}
