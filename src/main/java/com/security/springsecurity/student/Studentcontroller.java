package com.security.springsecurity.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class Studentcontroller {

  private static final List<Student> list = Arrays.asList(new Student(1,"Ravi"),new Student(2,"Tejaa"),new Student(3,"youth"));
@GetMapping("/{id}")
    public Student getstudentbyid(@PathVariable Integer id){
return list.stream().filter(student -> id.equals(student.getId())).findFirst().orElseThrow(()->new IllegalStateException("Student"+id+"doesn'texist"));
    }
}
