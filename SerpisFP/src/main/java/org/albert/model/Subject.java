package org.albert.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Class Subject. Entity Subject.
 */
@Entity
@Table(name = "MODULO_AL15")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @Column(name = "CODMODULO", nullable = false)
    private int subjectCode;

    @Column(name = "DESCRIPCION")
    private String description;

    @Column(name = "NUMHORAS")
    private int numHours;

    @OneToMany(mappedBy = "subject")
    private List<Enrollment> enrollments;

    public Subject(int subjectCode, String description, int numHours) {
        this.subjectCode = subjectCode;
        this.description = description;
        this.numHours = numHours;
    }
}
