package com.alekseysamoylov.integration.protobuf

import com.alekseysamoylov.integration.model.ProtobufTraining.Course


class CourseRepository(val courses: Map<Int, Course>) {

  fun getCourse(id: Int): Course? {
    return courses[id]
  }
}
