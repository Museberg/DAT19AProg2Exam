package maus.dat19a.exam.controllers;

import maus.dat19a.exam.models.AJAXStudent;
import maus.dat19a.exam.models.Student;
import maus.dat19a.exam.models.Supervisor;
import maus.dat19a.exam.services.IStudentService;
import maus.dat19a.exam.services.ISupervisorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
public class StudentsController {

    private IStudentService studentService;
    private ISupervisorService supervisorService;

    public StudentsController(IStudentService studentService, ISupervisorService supervisorService) {
        this.studentService = studentService;
        this.supervisorService = supervisorService;
    }


    @PostMapping("/addStudent")
    public ResponseEntity<AJAXStudent> addStudent(@RequestBody AJAXStudent ajaxStudent){
        // E-mail must be unique
        for(Student s : studentService.findAll()){
            if(s.getEmail().equalsIgnoreCase(ajaxStudent.getEmail())){
                return new ResponseEntity(ajaxStudent, HttpStatus.FORBIDDEN);
            }
        }

        System.out.println("STUDENT: " + ajaxStudent);;
        // Getting Supervisor from DB
        Supervisor supervisor = supervisorService.findById(ajaxStudent.getSupervisorId()).get();

        // Creating actual Student object from AJAXStudent
        Student student = new Student(ajaxStudent.getName(), ajaxStudent.getEmail(), supervisor);

        // Saving student to DB
        studentService.save(student);
        // Adding Student to Supervisor's list of students
        supervisor.getStudents().add(student);
        // Saving Supervisor to DB
        supervisorService.save(supervisor);

        // Setting new ID from DB to AJAXStudent
        ajaxStudent.setId(student.getId());
        return ResponseEntity.ok(ajaxStudent);
    }

    @PostMapping("/deleteStudent")
    public ResponseEntity<AJAXStudent> deleteStudent(@RequestBody AJAXStudent ajaxStudent){
        // Getting Student and his Supervisor by the Students ID
        Student student = studentService.findById(ajaxStudent.getId()).get();
        Supervisor supervisor = supervisorService.findById(student.getSupervisor().getId()).get();

        // Removing Student from the Supervisor's list of students
        supervisor.getStudents().remove(student);
        supervisorService.save(supervisor);

        // Removing Student from DB
        studentService.delete(student);

        return ResponseEntity.ok(ajaxStudent);
    }

    @PostMapping("/updateStudent")
    public ResponseEntity<AJAXStudent> updateStudent(@RequestBody AJAXStudent ajaxStudent){
        System.out.println(ajaxStudent);

        Student student = studentService.findById(ajaxStudent.getId()).get();
        // If Supervisor has changed, we must update the relation
        if(student.getSupervisor().getId() != ajaxStudent.getSupervisorId()){
            // Removing from old Supervisor's list of students
            Supervisor oldSupervisor = student.getSupervisor();
            oldSupervisor.getStudents().remove(student);
            supervisorService.save(oldSupervisor);

            // Adding to new Supervisor's list of students
            Supervisor newSupervisor = supervisorService.findById(ajaxStudent.getSupervisorId()).get();
            newSupervisor.getStudents().add(student);
            supervisorService.save(newSupervisor);

            // Saving Student with new values and new Supervisor
            student = new Student(ajaxStudent.getId(), ajaxStudent.getName(), ajaxStudent.getEmail(), newSupervisor);
        }
        else{ // Supervisor has not changed
            // Creating new Student object with updated values
            student = new Student(ajaxStudent.getId(), ajaxStudent.getName(), ajaxStudent.getEmail(), student.getSupervisor());
        }
        // Saving new Student
        studentService.save(student);
        return ResponseEntity.ok(ajaxStudent);
    }

    @PostMapping("/getAll")
    public List<AJAXStudent> getStudents(){
        List<Student> students = new ArrayList<>(studentService.findAll());
        List<AJAXStudent> ajaxStudents = new ArrayList<>();
        for(Student s : students){
            AJAXStudent ajaxStudent = new AJAXStudent(s.getId(), s.getName(), s.getEmail(), s.getSupervisor().getId());
            ajaxStudents.add(ajaxStudent);
        }
        Collections.sort(ajaxStudents);
        return ajaxStudents;
    }


}
