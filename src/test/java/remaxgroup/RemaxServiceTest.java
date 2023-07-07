package remaxgroup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import remaxgroup.dto.RemaxDTO;
import remaxgroup.mapper.RemaxMapper;
import remaxgroup.model.Remax;
import remaxgroup.repository.RemaxRepository;
import remaxgroup.service.RemaxService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RemaxServiceTest {

    @InjectMocks
    private RemaxService remaxService;

    @Mock
    RemaxRepository remaxRepository;

    @Mock
    RemaxMapper remaxMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll(){

        Remax remax1 = new Remax(1L,"apartment1","location1","sell",1000);
        Remax remax2 = new Remax(2L,"apartment2","location2","rent",500);
        List<Remax> remaxList = Arrays.asList(remax1,remax2);

        when(remaxRepository.findAll()).thenReturn(remaxList);
        when(remaxMapper.toDto(remax1)).thenReturn(new RemaxDTO(1L,"apartment1","location1","sell",1000));
        when(remaxMapper.toDto(remax2)).thenReturn(new RemaxDTO(2L,"apartment2","location2","rent",500));

        List<RemaxDTO> result = remaxService.getAll();

        assertEquals(remaxList.size(),result.size());

    }

    @Test
    public void testAddApartment(){

        RemaxDTO remaxDTO = new RemaxDTO(1L,"apartment1","location1","sell",1000);
        Remax remax = new Remax(1L,"apartment1","location1","sell",1000);

        when(remaxRepository.save(remax)).thenReturn(remax);
        when(remaxMapper.toEntity(remaxDTO)).thenReturn(remax);
        when(remaxMapper.toDto(remax)).thenReturn(remaxDTO);

        RemaxDTO result = remaxService.addApartments(remaxDTO);

        assertEquals(remaxDTO, result);
    }

    @Test
    public void testGetById(){
        Long id = 1L;
        RemaxDTO remaxDTO = new RemaxDTO(1L,"apartment1","location1","sell",1000);
        Remax remax = new Remax(1L,"apartment1","location1","sell",1000);

        when(remaxRepository.findById(id)).thenReturn(Optional.of(remax));
        when(remaxMapper.toDto(remax)).thenReturn(remaxDTO);

        RemaxDTO result = remaxService.getById(id);

        assertEquals(remaxDTO, result);
    }

    @Test
    public void testUpdateApartment(){

        Long id = 1L;
        RemaxDTO updatedApartmentDTO = new RemaxDTO(id,"apartment2","location2","rent",500);
        Remax updatedApartment = new Remax(id,"apartment2","location2","rent",500);

        when(remaxRepository.findById(id)).thenReturn(Optional.of(updatedApartment));
        when(remaxRepository.save(any(Remax.class))).thenReturn(updatedApartment);
        when(remaxMapper.toDto(updatedApartment)).thenReturn(updatedApartmentDTO);

        RemaxDTO result = remaxService.updateApartment(id, updatedApartmentDTO);

        assertEquals(updatedApartmentDTO, result);
    }

    @Test
    public void testDeleteById(){
        Long id = 1L;

        remaxService.deleteById(id);

        verify(remaxRepository, times(1)).deleteById(id);
    }
}
