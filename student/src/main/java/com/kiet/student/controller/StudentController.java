package com.kiet.student.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private Optional<Student> findById(int studentId) {
        return students.stream().filter(s -> s.getStudentId() == studentId).findFirst();
    }

    private ResponseEntity<Map<String, String>> notFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Student not found"));
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable int studentId) {
        Optional<Student> student = findById(studentId);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        }
        return notFound();
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable int studentId, @RequestBody Student updated) {
        Optional<Student> existing = findById(studentId);
        if (existing.isEmpty()) {
            return notFound();
        }
        Student student = existing.get();
        student.setName(updated.getName());
        student.setEmail(updated.getEmail());
        student.setBranch(updated.getBranch());
        student.setYear(updated.getYear());
        student.setCgpa(updated.getCgpa());
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable int studentId) {
        boolean removed = students.removeIf(s -> s.getStudentId() == studentId);
        if (removed) {
            return ResponseEntity.ok(Map.of("message", "Student deleted successfully"));
        }
        return notFound();
    }

    @GetMapping("/search")
    public List<Student> searchByBranchAndYear(@RequestParam String branch, @RequestParam int year) {
        return students.stream()
                .filter(s -> s.getBranch().toLowerCase().startsWith(branch.toLowerCase()) && s.getYear() == year)
                .toList();
    }

    @GetMapping("/topper")
    public List<Student> findByCgpa(@RequestParam double cgpa) {
        return students.stream().filter(s -> s.getCgpa() >= cgpa).toList();
    }
}
