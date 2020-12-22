package maus.dat19a.exam.controllers;

import maus.dat19a.exam.models.Student;
import maus.dat19a.exam.models.Supervisor;
import maus.dat19a.exam.services.IStudentService;
import maus.dat19a.exam.services.ISupervisorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class IndexController {

    private IStudentService studentService;
    private ISupervisorService supervisorService;

    public IndexController(IStudentService studentService, ISupervisorService supervisorService) {
        this.studentService = studentService;
        this.supervisorService = supervisorService;
    }

    @GetMapping("/")
    private String index(Model model){
        List<Student> students = new ArrayList<>(studentService.findAll());
        Collections.sort(students);
        model.addAttribute("students", students);

        List<Supervisor> supervisors = new ArrayList<>(supervisorService.findAll());
        Collections.sort(supervisors);
        model.addAttribute("supervisors", supervisors);

        model.addAttribute("student", new Student());
        return "index";
    }
}
