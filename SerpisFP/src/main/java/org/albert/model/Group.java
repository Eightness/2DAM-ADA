package org.albert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "CODGRUPO", nullable = false)
    private int groupCode;

    @Column(name = "DESCRIPCION", length = 50)
    private String description;

    @Column(name = "AULA", length = 10)
    private String classroom;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;
}
