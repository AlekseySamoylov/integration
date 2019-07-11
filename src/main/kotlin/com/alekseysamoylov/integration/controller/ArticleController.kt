package com.alekseysamoylov.integration.controller

import com.alekseysamoylov.integration.Article
import com.alekseysamoylov.integration.ArticleRepository
import com.alekseysamoylov.integration.User
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$BASE_URI/article")
class ArticleController(private val repository: ArticleRepository) {

  @GetMapping
  @HystrixCommand(fallbackMethod = "fallbackFindAll", commandProperties = [
    HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
  ])
  fun findAll(): List<Article> {
    Thread.sleep(3000)
    return repository.findAllByOrderByAddedAtDesc()
  }

  fun fallbackFindAll() = listOf<Article>(Article("FallbackTitle", "FallbackHeadline", "", User()))

  @GetMapping("/{slug}")
  fun findOne(@PathVariable slug: String) =
      repository.findBySlug(slug) ?: throw IllegalArgumentException("Wrong article slug provided")

}
