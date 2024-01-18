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
    @Column(name = "IDMATRICULA", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "NIA", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "CODMODULO", nullable = false)
    private Subject subject;

    @Column(name = "DESCRIPCION", length = 50)
    private String description;
}
