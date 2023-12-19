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
@Table(name = "product")
public class Product implements Serializable {

    //SerialVersion
    private static final long SerialVersionUID = 1L;

    //Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "sectionId", referencedColumnName = "sectionId")
    Section section;  //This is the foreign key
    private double price;
    private String description;

    //Custom constructor
    public Product(String name, Section section, double price, String description) {
        this.name = name;
        this.section = section;
        this.price = price;
        this.description = description;
    }
}
