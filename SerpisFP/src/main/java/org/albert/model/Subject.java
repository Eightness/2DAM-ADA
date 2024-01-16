package org.albert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class Subject. Entity Subject.
 */
@Entity
@Table(name = "MODULO_AL15")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @Column(name = "CODMODULO")
    private String subjectCode;

    @Column(name = "DESCRIPCION")
    private String description;

    @Column(name = "NUMHORAS")
    private int numHours;
}
