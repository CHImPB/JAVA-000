package com.zhanghongyu.springboot.hikari;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hikari")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/crud")
    public void CRUD(){
        System.out.println("------Records Creation--------" );
        studentService.create("Zara", 11);
        studentService.create("Nuha", 2);
        studentService.create("Ayan", 15);
        System.out.println("------Listing Multiple Records--------" );
        List<Student> students = studentService.listStudents();
        for (Student record : students) {
            System.out.print("ID : " + record.getId() );
            System.out.print(", Name : " + record.getName() );
            System.out.println(", Age : " + record.getAge());
        }
        System.out.println("----Updating Record with ID = 2 -----" );
        studentService.update(2, 20);
        System.out.println("----Listing Record with ID = 2 -----" );
    }
}
