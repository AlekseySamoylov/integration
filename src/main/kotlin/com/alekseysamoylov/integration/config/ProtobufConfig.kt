package com.alekseysamoylov.integration.config

import com.alekseysamoylov.integration.model.ProtobufTraining.Course
import com.alekseysamoylov.integration.model.ProtobufTraining.Student
import com.alekseysamoylov.integration.model.ProtobufTraining.Student.PhoneNumber
import com.alekseysamoylov.integration.model.ProtobufTraining.Student.PhoneType
import com.alekseysamoylov.integration.protobuf.CourseRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import java.util.*


@Configuration
class ProtobufConfig {
  @Bean
  fun protobufHttpMessageConverter(): ProtobufHttpMessageConverter {
    return ProtobufHttpMessageConverter()
  }

  @Bean
  fun createTestCourses(): CourseRepository {
    val courses = HashMap<Int, Course>()
    val course1 = Course.newBuilder()
        .setId(1)
        .setCourseName("REST with Spring")
        .addAllStudent(createTestStudents())
        .build()
    val course2 = Course.newBuilder()
        .setId(2)
        .setCourseName("Learn Spring Security")
        .addAllStudent(ArrayList())
        .build()
    courses[course1.id] = course1
    courses[course2.id] = course2
    return CourseRepository(courses)
  }

  private fun createTestStudents(): List<Student> {
    val phone1 = createPhone("123456", PhoneType.MOBILE)
    val student1 = createStudent(1, "John", "Doe", "john.doe@baeldung.com", Arrays.asList(phone1))

    val phone2 = createPhone("234567", PhoneType.LANDLINE)
    val student2 = createStudent(2, "Richard", "Roe", "richard.roe@baeldung.com", Arrays.asList(phone2))

    val phone31 = createPhone("345678", PhoneType.MOBILE)
    val phone32 = createPhone("456789", PhoneType.LANDLINE)
    val student3 = createStudent(3, "Jane", "Doe", "jane.doe@baeldung.com", Arrays.asList(phone31, phone32))

    return listOf(student1, student2, student3)
  }

  private fun createStudent(id: Int, firstName: String, lastName: String, email: String, phones: List<PhoneNumber>): Student {
    return Student.newBuilder().setId(id).setFirstName(firstName).setLastName(lastName).setEmail(email).addAllPhone(phones).build()
  }

  private fun createPhone(number: String, type: PhoneType): PhoneNumber {
    return PhoneNumber.newBuilder().setNumber(number).setType(type).build()
  }
}
