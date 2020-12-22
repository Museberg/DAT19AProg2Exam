package maus.dat19a.exam.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class Supervisor implements Comparable<Supervisor>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<Student> students;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Student> getStudents() {
        if(students == null){
            return new ArrayList<>();
        }
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public int compareTo(Supervisor s) {
        return Comparator.comparing(Supervisor::getId)
                .compare(this, s);
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                "id=" + id +
                '}';
    }
}
