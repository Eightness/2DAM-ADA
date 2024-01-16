package org.albert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class Group. Entity Group.
 */
@Entity
@Table(name = "GRUPO_AL15")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @Column(name = "CODGRUPO")
    private int groupCode;

    @Column(name = "DESCRIPCION")
    private String description;

    @Column(name = "AULA")
    private String classroom;
}
