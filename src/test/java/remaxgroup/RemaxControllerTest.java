package remaxgroup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import remaxgroup.controller.RemaxController;
import remaxgroup.dto.RemaxDTO;
import remaxgroup.mapper.RemaxMapper;
import remaxgroup.model.Remax;
import remaxgroup.service.RemaxService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(RemaxController.class)
public class RemaxControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RemaxService remaxService;

    @Mock
    private RemaxMapper remaxMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        RemaxDTO remaxDTO1 = new RemaxDTO(1L,"apartment1","location1","sell",1000);
        RemaxDTO remaxDTO2 = new RemaxDTO(2L,"apartment2","location2","rent",500);
        List<RemaxDTO> remaxDTOList = Arrays.asList(remaxDTO1,remaxDTO2);

        when(remaxService.getAll()).thenReturn(remaxDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/apartments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].homeType").value("apartment1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].location").value("location1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sellOrRent").value("sell"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].homeType").value("apartment2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].location").value("location2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].sellOrRent").value("rent"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(500));
    }

    @Test
    public void testAddApartment() throws Exception {

        RemaxDTO remaxDTO = new RemaxDTO(1L,"apartment1","location1","sell",1000);

        when(remaxService.addApartments(remaxDTO)).thenReturn(remaxDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/apartments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"homeType\": \"apartment1\", \"location\": \"location1\", \"sellOrRent\": \"sell\", \"price\": 1000 }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.homeType").value("apartment1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("location1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sellOrRent").value("sell"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1000));
    }

    @Test
    public void testGetById() throws Exception {
        Long id = 1L;
        RemaxDTO remaxDTO = new RemaxDTO(id,"apartment1","location1","sell",1000);

        when(remaxService.getById(id)).thenReturn(remaxDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/apartments/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.homeType").value("apartment1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("location1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sellOrRent").value("sell"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1000));
    }

    @Test
    public void testUpdateApartment() throws Exception{
        Long id = 1L;
        RemaxDTO updatedApartmentDto = new RemaxDTO(id, "apartment2", "location2", "rent", 500);

        when(remaxService.updateApartment(id,updatedApartmentDto)).thenReturn(updatedApartmentDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/apartments/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"homeType\": \"apartment2\", \"location\": \"location2\", \"sellOrRent\": \"rent\", \"price\": 500 }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.homeType").value("apartment2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("location2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sellOrRent").value("rent"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(500));
    }

    @Test
    public void testDeleteById() throws Exception{
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/apartments/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }












}
