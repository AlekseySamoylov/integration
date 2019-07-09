package com.alekseysamoylov.integration

import org.springframework.data.repository.CrudRepository


interface ArticleRepository : CrudRepository<Article, Long> {
  fun findBySlug(slug: String): Article?
  fun findAllByOrderByAddedAtDesc(): List<Article>
}

interface UserRepository : CrudRepository<User, Long> {
  fun findByLogin(login: String): User
}
