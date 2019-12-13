package com.isg.soa.Projet.MedicinalTunisianPlants.Controllers;

import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Plant;
import com.isg.soa.Projet.MedicinalTunisianPlants.Repositories.PlantRepository;
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
@RequestMapping("/plant")
public class PlantController {
    private final Logger log = LoggerFactory.getLogger(FlowerController.class);
    @Autowired
    private final PlantRepository P_repo;

    public PlantController(PlantRepository p_repo) {
        P_repo = p_repo;
    }
    @GetMapping("/allPlants")
    public Collection<Plant> allPlants(){
        return P_repo.findAll();
    }

    @GetMapping("/plant/{id}")
    public ResponseEntity<?> getPlantById(@PathVariable("id") Long Id){
        Optional<Plant> plant =P_repo.findById(Id);
        return plant.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //plant

    @GetMapping("/plantByName/{Name}")
    public Collection<Plant> getPlantByName(@PathVariable String Name){
        return P_repo.findAll().stream().filter(x ->x.getName().equals(Name)).collect(Collectors.toList());
    }
    @PostMapping("/newPlant")
    public ResponseEntity<Plant> addPlant(@Valid @RequestBody Plant plant) throws URISyntaxException {
        log.info("request for adding new plant ()",plant);
        Plant result = P_repo.save(plant);
        return ResponseEntity.created(new URI("/newplant" + result.getId())).body(result);
    }
    @PutMapping("/updatePlant/{id}")

    public ResponseEntity<Plant> updatePlant(@Valid @RequestBody Plant plant, @PathVariable("id") long Id) {
        log.info("Request for updating a plant {}", plant);

        Optional<Plant> plantOptional = P_repo.findById(Id);

        if (plantOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Plant P1 = plantOptional.get();
        P1.setName(plant.getName());
        P1.setPrice(plant.getPrice());
        P1.setOrigin(plant.getOrigin());
        P1.setFlower(plant.getFlower());
        P1.setDescription(plant.getDescription());
        P1.setUsesOfPlant(plant.getUsesOfPlant());
        P1.setPhotos(plant.getPhotos());
        P1.setColor(plant.getColor());
        Plant result = P_repo.save(P1);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/deletePlant/{id}")
    public Map<String, Boolean> deletePlant(@PathVariable Long id)
            throws ResourceNotFoundException {
        Plant plant = P_repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plant not found for this id : " + id));

        P_repo.delete(plant);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
    @DeleteMapping("/removePlantByName/{Name}")
    public ResponseEntity<?> deletePlantByName(@PathVariable("Name") String Name) {
        log.info("Request for removing product {}", Name);
        List<Plant> lstplants = P_repo.findAll().stream()
                .filter(x -> x.getName().equals(Name))
                .collect(Collectors.toList());

        for (Plant p : lstplants)
            P_repo.deleteById(p.getId());
        return ResponseEntity.ok().build();

    }
}
