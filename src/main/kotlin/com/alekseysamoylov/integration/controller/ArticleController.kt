package com.alekseysamoylov.integration.controller

import com.alekseysamoylov.integration.ArticleRepository
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$BASE_URI/article")
class ArticleController(private val repository: ArticleRepository) {

  @GetMapping
  fun findAll() = repository.findAllByOrderByAddedAtDesc()

  @GetMapping("/{slug}")
  fun findOne(@PathVariable slug: String) =
      repository.findBySlug(slug) ?: throw IllegalArgumentException("Wrong article slug provided")

}
