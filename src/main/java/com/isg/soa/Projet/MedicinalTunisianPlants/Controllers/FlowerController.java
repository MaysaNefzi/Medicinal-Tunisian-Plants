package com.isg.soa.Projet.MedicinalTunisianPlants.Controllers;

import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Flower;
import com.isg.soa.Projet.MedicinalTunisianPlants.Repositories.FlowerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flower")
public class FlowerController {
    private final Logger log = LoggerFactory.getLogger(FlowerController.class);
    @Autowired
    private final FlowerRepository F_repo;

    public FlowerController(FlowerRepository f_repo) {
        F_repo = f_repo;
    }
    @GetMapping("/allFlowers")
    public Collection<Flower>allFlowers(){
        return F_repo.findAll();
    }
    @GetMapping("/flower/{id}")
    public ResponseEntity<?> getFlowerById(@PathVariable("id") Long Id){
        Optional<Flower> flower =F_repo.findById(Id);
        return flower.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/flowerByName/{Name}")
    public Collection<Flower> getFlowerByName(@PathVariable String Name){
        return F_repo.findAll().stream().filter(x ->x.getName().equals(Name)).collect(Collectors.toList());
    }
    @PostMapping("/newFlower")
    public ResponseEntity<Flower> addFlower(@Valid @RequestBody Flower flower) throws URISyntaxException {
        log.info("request for adding new flower ()",flower);
        Flower result = F_repo.save(flower);
        return ResponseEntity.created(new URI("/newFlower" + result.getId())).body(result);
    }
    @PutMapping("/updateFlower/{id}")

    public ResponseEntity<Flower> updateFlower(@Valid @RequestBody Flower flower, @PathVariable("id") long Id) {
        log.info("Request for updating a flower {}", flower);

        Optional<Flower> flowerOptional = F_repo.findById(Id);

        if (flowerOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Flower F1 = flowerOptional.get();
        F1.setName(flower.getName());
        F1.setPrice(flower.getPrice());
        F1.setSmell(flower.getSmell());
        F1.setPlant(flower.getPlant());
        F1.setUsesOfFlower(flower.getUsesOfFlower());
        Flower result = F_repo.save(F1);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/deleteFlower/{id}")

    public Map<String, Boolean> deleteFlower(@PathVariable Long id)
            throws ResourceNotFoundException {
        Flower flower = F_repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flower not found for this id : " + id));
        F_repo.delete(flower);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
    @DeleteMapping("/removeFlowerByName/{Name}")
    public ResponseEntity<?> deleteFlowerByName(@PathVariable("Name") String Name) {
        log.info("Request for removing product {}", Name);
        List<Flower> lstflowers = F_repo.findAll().stream()
                .filter(x -> x.getName().equals(Name))
                .collect(Collectors.toList());

        for (Flower f : lstflowers)
            F_repo.deleteById(f.getId());
        return ResponseEntity.ok().build();

    }
}
