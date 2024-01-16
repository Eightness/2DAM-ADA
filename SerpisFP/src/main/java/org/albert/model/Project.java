package org.albert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * Class Project. Entity Project.
 */
@Entity
@Table(name = "PROYECTO_CONVOCATORIA_AL15")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @Column(name = "CODPROYECTO")
    private String projectCode;

    @Column(name = "TITULO")
    private String title;

    @ManyToOne
    @JoinColumn(name = "NIA", nullable = false, unique = true)
    private Student student;
}
