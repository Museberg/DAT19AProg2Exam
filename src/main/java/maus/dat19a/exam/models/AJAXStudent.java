package maus.dat19a.exam.models;

import java.util.Comparator;

public class AJAXStudent implements Comparable<AJAXStudent>{

    private Long id;
    private String name;
    private String email;
    private long SupervisorId;

    public AJAXStudent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public long getSupervisorId() {
        return SupervisorId;
    }

    public void setSupervisorId(long supervisorId) {
        SupervisorId = supervisorId;
    }

    @Override
    public String toString() {
        return "AJAXStudent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", SupervisorId=" + SupervisorId +
                '}';
    }

    public AJAXStudent(Long id, String name, String email, long supervisorId) {
        this.id = id;
        this.name = name;
        this.email = email;
        SupervisorId = supervisorId;
    }

    @Override
    public int compareTo(AJAXStudent s) {
        return Comparator.comparing(AJAXStudent::getId)
                .compare(this, s);
    }
}
