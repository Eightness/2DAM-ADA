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
    @Column(name = "CODPROYECTO", nullable = false)
    private String id;

    @Column(name = "TITULO", length = 200)
    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nia", referencedColumnName = "NIA")
    private Student student;
}
