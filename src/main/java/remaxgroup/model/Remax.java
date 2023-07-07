package remaxgroup.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Remax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String homeType;
    private String location;
    private String sellOrRent;
    private int price;

    public Remax() {}

    public Remax(Long id, String homeType, String location, String sellOrRent, int price){ }

}
