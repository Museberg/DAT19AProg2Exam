package maus.dat19a.exam;

import maus.dat19a.exam.models.Student;
import maus.dat19a.exam.models.Supervisor;
import maus.dat19a.exam.services.IStudentService;
import maus.dat19a.exam.services.ISupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
// Adds some demo data to the DB. DB is running on 127.0.0.1:3306 and is simply called 'exam'
public class DemoData {

    private IStudentService studentService;
    private ISupervisorService supervisorService;

    public DemoData(IStudentService studentService, ISupervisorService supervisorService) {
        this.studentService = studentService;
        this.supervisorService = supervisorService;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event){
        // Adding some Supervisors
        Supervisor sup1 = new Supervisor();
        Supervisor sup2 = new Supervisor();
        Supervisor sup3 = new Supervisor();
        // Saving the Supervisors
        supervisorService.save(sup1);
        supervisorService.save(sup2);
        supervisorService.save(sup3);

        // Adding some Students
        Student stud1 = new Student("John Doe", "John@doe.com", sup1);
        Student stud2 = new Student("Mary Jane", "Mary@jane.com", sup2);
        Student stud3 = new Student("Gavin Baker", "Gavin@baker.com", sup3);
        Student stud4 = new Student("Paul Glover", "Poul@glover.com", sup1);
        // Saving the Students
        studentService.save(stud1);
        studentService.save(stud2);
        studentService.save(stud3);
        studentService.save(stud4);

        // Adding the Students to the Supervisor and saving them to the DB
        sup1.getStudents().add(stud1);
        sup2.getStudents().add(stud2);
        sup3.getStudents().add(stud3);
        sup1.getStudents().add(stud4);
        // Saving the Supervisors with their newly added Students
        supervisorService.save(sup1);
        supervisorService.save(sup2);
        supervisorService.save(sup3);

    }
}
