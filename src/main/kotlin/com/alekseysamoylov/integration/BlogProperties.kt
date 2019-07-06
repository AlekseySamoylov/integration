package com.alekseysamoylov.integration

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties("blog")
class BlogProperties {

  lateinit var title: String
  val banner = Banner()

  class Banner {
    var title: String? = null
    lateinit var content: String
  }

}
