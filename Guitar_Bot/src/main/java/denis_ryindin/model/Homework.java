package denis_ryindin.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "homework")
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Homework() {}
    public Homework(String description, LocalDate deadline, Student student) {
        this.description = description;
        this.deadline = deadline;
        this.student = student;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    @Override
    public String toString() {
        return description + " (до " + deadline + ")";
    }
}
