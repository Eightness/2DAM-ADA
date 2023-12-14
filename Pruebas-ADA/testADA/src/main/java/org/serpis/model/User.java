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
public class User implements Serializable {

    //SerialVersion
    private static final long SerialVersionUID = 1L;

    //Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;
    private String name;
    private String password;
    @OneToOne
    @JoinColumn(name = "personId", insertable = false, updatable = false)
    private int personId;   //This is the foreign key

    //Custom constructor
    public User(String name, String password, int personId) {
        this.name = name;
        this.password = password;
        this.personId = personId;
    }
}
