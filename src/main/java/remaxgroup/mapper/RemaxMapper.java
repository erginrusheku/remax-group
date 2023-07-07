package remaxgroup.mapper;

import org.springframework.stereotype.Component;
import remaxgroup.dto.RemaxDTO;
import remaxgroup.model.Remax;

@Component
public class RemaxMapper {

    public RemaxDTO toDto(Remax remax){

        RemaxDTO remaxDTO = new RemaxDTO();
        remaxDTO.setId(remax.getId());
        remaxDTO.setHomeType(remax.getHomeType());
        remaxDTO.setLocation(remax.getLocation());
        remaxDTO.setSellOrRent(remax.getSellOrRent());
        remaxDTO.setPrice(remax.getPrice());

        return remaxDTO;
    }

    public Remax toEntity(RemaxDTO remaxDTO){

        Remax remax = new Remax();
        remax.setId(remaxDTO.getId());
        remax.setHomeType(remaxDTO.getHomeType());
        remax.setLocation(remaxDTO.getLocation());
        remax.setSellOrRent(remaxDTO.getSellOrRent());
        remax.setPrice(remaxDTO.getPrice());

        return remax;
    }

}
