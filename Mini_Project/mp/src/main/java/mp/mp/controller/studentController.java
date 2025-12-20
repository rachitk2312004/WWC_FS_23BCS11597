package mp.mp.controller;
import mp.mp.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/students")
public class studentController {
    private final List<Student> students = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Name must not be null or empty");
        }
        
        if (student.getCourse() == null || student.getCourse().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Course must not be null or empty");
        }

        Optional<Student> existingStudent = students.stream()
                .filter(s -> s.getId() == student.getId())
                .findFirst();
        
        if (existingStudent.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Student with ID " + student.getId() + " already exists");
        }

        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Student registered successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable int id) {
        Optional<Student> student = students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student with ID " + id + " not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        Optional<Student> studentToDelete = students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        
        if (studentToDelete.isPresent()) {
            students.remove(studentToDelete.get());
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student with ID " + id + " not found");
        }
    }
}

