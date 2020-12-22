package maus.dat19a.exam.repos;

import maus.dat19a.exam.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface IStudentRepo extends CrudRepository<Student, Long> {
}
