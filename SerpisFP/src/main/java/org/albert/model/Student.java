package org.albert.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Class Student. Entity Student.
 */
@Entity
@Table(name = "alumno_al15")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "NIA", nullable = false, length = 10)
    private String nia;

    @Column(name = "NOMBRE", length = 50)
    private String name;

    @Column(name = "APELLIDOS", length = 50)
    private String surnames;

    @ManyToOne
    @JoinColumn(name = "CODGRUPO", nullable = false)
    private Group group;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    @OneToOne(mappedBy = "student")
    private Project project;

    public Student(String nia, String name, String surnames, Group group, Project project) {
        this.nia = nia;
        this.name = name;
        this.surnames = surnames;
        this.group = group;
        this.project = project;
    }
}
