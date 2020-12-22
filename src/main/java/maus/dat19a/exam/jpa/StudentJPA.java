package maus.dat19a.exam.jpa;

import maus.dat19a.exam.models.Student;
import maus.dat19a.exam.repos.IStudentRepo;
import maus.dat19a.exam.services.IStudentService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentJPA implements IStudentService {

    private final IStudentRepo studentRepo;

    public StudentJPA(IStudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public Set<Student> findAll() {
        Set<Student> set = new HashSet<>();
        studentRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Student save(Student object) {
        return studentRepo.save(object);
    }

    @Override
    public void delete(Student object) {
        studentRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        studentRepo.deleteById(aLong);
    }

    @Override
    public Optional<Student> findById(Long aLong) {
        return studentRepo.findById(aLong);
    }
}
