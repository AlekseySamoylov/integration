package com.alekseysamoylov.integration.controller

import com.alekseysamoylov.integration.model.ProtobufTraining.Course
import com.alekseysamoylov.integration.protobuf.CourseRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("$API_URI/course")
class CourseController(val courseRepo: CourseRepository) {

  @GetMapping(value = ["/{id}"], produces = [PROTOBUF_MEDIA_TYPE_VALUE])
  @ResponseBody
  fun customer(@PathVariable id: Int): ResponseEntity<Course> {
    return courseRepo.getCourse(id)?.let {
      ResponseEntity(it, HttpStatus.OK)
    } ?: ResponseEntity(HttpStatus.NOT_FOUND)
  }

  companion object {
    const val PROTOBUF_MEDIA_TYPE_VALUE = "application/x-protobuf"
  }
}
