/**
 * @author Albert Lozano Blasco
 * @version 1.0
 */

package org.serpis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person implements Serializable {

    //SerialVersion
    private static final long SerialVersionUID = 1L;

    //Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personId")
    private int personId;
    private String name;
    private String surnames;
    private String email;
    private String phone;

    //Custom constructor
    public Person(String name, String surnames, String email, String phone) {
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.phone = phone;
    }
}
