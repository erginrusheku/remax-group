package remaxgroup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RemaxDTO {

    private Long id;
    private String homeType;
    private String location;
    private String sellOrRent;
    private int price;
}
