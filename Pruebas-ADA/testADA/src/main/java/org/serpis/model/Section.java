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
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "section")
public class Section implements Serializable {

    //SerialVersion
    private static final long SerialVersionUID = 1L;

    //Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sectionId")
    private int sectionId;
    private String section;
    private String inCharge;
    @OneToMany(mappedBy = "section")
    private List<Product> products;

    //Custom constructor
    public Section(String section, String inCharge) {
        this.section = section;
        this.inCharge = inCharge;
    }
}
