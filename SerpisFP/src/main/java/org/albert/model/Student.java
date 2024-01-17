package org.albert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name = "NIA")
    private String nia;

    @Column(name = "NOMBRE")
    private String name;

    @Column(name = "APELLIDOS")
    private String surnames;

    @ManyToOne
    @JoinColumn(name = "CODGRUPO", nullable = false)
    private Group group;
}
