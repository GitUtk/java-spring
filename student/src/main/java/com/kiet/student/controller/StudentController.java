package com.kiet.student.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiet.student.model.Student;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(101, "Rahul Sharma", "rahul@kiet.edu", "CSE(AI & ML)", 2, 8.65));
        students.add(new Student(102, "Priya Singh", "priya@kiet.edu", "CSE", 2, 9.10));
        students.add(new Student(103, "Ankit Verma", "ankit@kiet.edu", "ECE", 3, 7.85));
        students.add(new Student(104, "Sneha Gupta", "sneha@kiet.edu", "CSE(AI)", 2, 9.25));
        students.add(new Student(105, "Rohan Mehta", "rohan@kiet.edu", "IT", 4, 8.20));
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {
        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                return s;
            }
        }
        return null;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable int studentId, @RequestBody Student updated) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                student.setName(updated.getName());
                student.setEmail(updated.getEmail());
                student.setBranch(updated.getBranch());
                student.setYear(updated.getYear());
                student.setCgpa(updated.getCgpa());
                return student;
            }
        }
        return null;
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable int studentId) {
        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                students.remove(s);
                return "Student deleted successfully";
            }
        }
        return "Student not found";
    }

    @GetMapping("/search")
    public List<Student> searchByBranchAndYear(@RequestParam String branch, @RequestParam int year) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getBranch().equalsIgnoreCase(branch) && s.getYear() == year) {
                result.add(s);
            }
        }
        return result;
    }

    @GetMapping("/topper")
    public List<Student> findByCgpa(@RequestParam double cgpa) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getCgpa() >= cgpa) {
                result.add(s);
            }
        }
        return result;
    }
}
