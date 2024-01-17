package org.albert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Class Enrollment. Entity Enrollment.
 */
@Entity
@Table(name = "MATRICULA_AL15")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {

    @Id
    @Column(name = "IDMATRICULA")
    private int enrollmentId;

    @Id
    @ManyToOne
    @JoinColumn(name = "NIA")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "CODMODULO")
    private Subject subject;

    @Column(name = "DESCRIPCION")
    private String description;
}
