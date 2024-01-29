package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    private List<Student> students = new ArrayList<>(){{
        add(new Student("Nathan", "King"));
        add(new Student("Dave", "Ames"));
    }};

    @GetMapping
    public List<Student> getAll() {
        return this.students;
    }
    @GetMapping("/{firstName}")
    public Student getStudent(@PathVariable(name = "firstName") String firstName) {
        for(Student s : this.students){
            if(s.getFirstName().equals(firstName)){
                return s;
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        this.students.add(student);

        return student;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable(name = "firstName") String firstName, @RequestBody Student student) {
        for (Student s : this.students) {
            if (s.getFirstName().equals(firstName)) {
                s.setFirstName(student.getFirstName());
                s.setLastName(student.getLastName());
                return s;
            }
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    public Student delete(@PathVariable (name = "firstName") String firstName) {
        for (Student s : this.students){
            if(s.getFirstName().equals(firstName)){
                this.students.remove(s);
                return s;
            }
        }
        return null;
    }
}
