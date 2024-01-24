package org.albert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

/**
 * Class Student. Entity Student.
 */
@Entity
@Table(name = "alumno_al15")
@Data
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

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Project project;
}
