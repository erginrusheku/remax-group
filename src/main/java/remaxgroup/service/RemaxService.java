package remaxgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import remaxgroup.dto.RemaxDTO;
import remaxgroup.mapper.RemaxMapper;
import remaxgroup.model.Remax;
import remaxgroup.repository.RemaxRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemaxService {

    private final RemaxRepository remaxRepository;
    private final RemaxMapper remaxMapper;

    @Autowired
    public RemaxService(RemaxRepository remaxRepository, RemaxMapper remaxMapper) {
        this.remaxRepository = remaxRepository;
        this.remaxMapper = remaxMapper;
    }

    public List<RemaxDTO> getAll() {
        List<Remax> remax = remaxRepository.findAll();
        List<RemaxDTO> remaxDTO = new ArrayList<>();

        for (Remax remax1 : remax) {
            RemaxDTO remaxDTO1 = remaxMapper.toDto(remax1);
            remaxDTO.add(remaxDTO1);
        }
            return remaxDTO;
    }

    public RemaxDTO addApartments(RemaxDTO remaxDTO) {
        Remax remax = remaxMapper.toEntity(remaxDTO);
        Remax savedRemax = remaxRepository.save(remax);

        return remaxMapper.toDto(savedRemax);
    }

    public RemaxDTO getById(Long id) {
        Remax remax = remaxRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Apartment not found"));

        return remaxMapper.toDto(remax);
    }

    public RemaxDTO updateApartment(Long id, RemaxDTO updateApartment) {
        Remax existingRemax = remaxRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Apartment not found with id: "+ id));

        existingRemax.setHomeType(updateApartment.getHomeType());
        existingRemax.setLocation(updateApartment.getLocation());
        existingRemax.setSellOrRent(updateApartment.getSellOrRent());
        existingRemax.setPrice(updateApartment.getPrice());

        Remax savedRemax = remaxRepository.save(existingRemax);

        return remaxMapper.toDto(savedRemax);
    }

    public void deleteById(Long id) { remaxRepository.deleteById(id); }
}
