package com.alekseysamoylov.integration

import com.alekseysamoylov.integration.rest.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class HtmlControllerIntegrationTest(
    @Autowired val restTemplate: TestRestTemplate,
    @Autowired val userRepository: UserRepository,
    @Autowired val articleRepository: ArticleRepository) {

  @BeforeAll
  fun setup() {
    val smaldini = userRepository.save(User("smaldini", "Stéphane", "Maldini"))
    articleRepository.save(Article(
        title = "Reactor Aluminium has landed",
        headline = "Lorem ipsum",
        content = "dolor sit amet",
        author = smaldini
    ))

    articleRepository.save(Article(
        title = "Reactor Bismuth is out",
        headline = "Lorem ipsum",
        content = "dolor sit amet",
        author = smaldini
    ))
  }

  @Test
  fun `Assert blog page title, content and status code`() {
    println(">> Assert blog page title, content and status code")
    val entity = restTemplate.getForEntity<String>("/")
    assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
    assertThat(entity.body).contains("<h1>Integration App</h1>", "Reactor")
  }

  @Test
  fun `Assert article page title, content and status code`() {
    println(">> Assert article page title, content and status code")
    val title = "Reactor Aluminium has landed"
    val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
    assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
    assertThat(entity.body).contains(title)
  }

  @AfterAll
  fun teardown() {
    println(">> Tear down")
  }

}

