package remaxgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import remaxgroup.dto.RemaxDTO;
import remaxgroup.service.RemaxService;

import java.util.List;

@RestController
@RequestMapping("/apartments")
public class RemaxController {

    private RemaxService remaxService;

    @Autowired
    public RemaxController(RemaxService remaxService){
        this.remaxService = remaxService;
    }

    @GetMapping
    public List<RemaxDTO> getAll() { return remaxService.getAll(); }

    @PostMapping
    public RemaxDTO addApartment(@RequestBody RemaxDTO remaxDTO) { return remaxService.addApartments(remaxDTO); }

    @GetMapping("/{id}")
    public RemaxDTO getById(@PathVariable Long id) { return remaxService.getById(id); }

    @PutMapping("/{id}")
    public RemaxDTO updateApartment(@PathVariable Long id, @RequestBody RemaxDTO updateApartment){
        return remaxService.updateApartment(id, updateApartment);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){ remaxService.deleteById(id); }
}
