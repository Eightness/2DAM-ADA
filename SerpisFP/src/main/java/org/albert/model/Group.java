package org.albert.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Class Group. Entity Group.
 */
@Entity
@Table(name = "GRUPO_AL15")
@Getter
@Setter
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

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    public Group(int groupCode, String description, String classroom) {
        this.groupCode = groupCode;
        this.description = description;
        this.classroom = classroom;
    }
}
