package com.alekseysamoylov.integration

import com.alekseysamoylov.integration.model.ProtobufTraining.Course
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProtobufCourseTest(@Autowired val restTemplate: TestRestTemplate) {

  @Test
  fun `Assert protobuf course`() {
    val course = restTemplate.getForEntity("http://localhost:8080/api/course/1", Course::class.java)
    println(course)
    assertThat(course.body!!.courseName).isEqualTo("REST with Spring")
  }
}
