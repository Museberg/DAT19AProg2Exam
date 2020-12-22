package maus.dat19a.exam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Comparator;

@Entity
public class Student implements Comparable<Student>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String email;

    @JsonIgnore
    @ManyToOne
    private Supervisor supervisor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public int compareTo(Student s) {
        return Comparator.comparing(Student::getId)
                .compare(this, s);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", supervisor=" + supervisor +
                '}';
    }

    public Student(String name, String email, Supervisor supervisor) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.supervisor = supervisor;
    }

    public Student(long id, String name, String email, Supervisor supervisor) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.supervisor = supervisor;
    }

    public Student() {
    }
}
