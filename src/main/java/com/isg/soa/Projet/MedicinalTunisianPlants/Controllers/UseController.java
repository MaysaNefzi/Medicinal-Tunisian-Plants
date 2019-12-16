package com.isg.soa.Projet.MedicinalTunisianPlants.Controllers;

import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Flower;
import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Plant;
import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Use;
import com.isg.soa.Projet.MedicinalTunisianPlants.Repositories.UseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/use")
public class UseController {
    private final Logger log = LoggerFactory.getLogger(UseController.class);
    @Autowired
    private final UseRepository U_repo;

    public UseController(UseRepository u_repo) {
        U_repo = u_repo;
    }
    @GetMapping("/allUses")
    public Collection<Use> allUses(){
        return U_repo.findAll();
    }
    @GetMapping("/flower/{id}")
    public ResponseEntity<?> getUseById(@PathVariable("id") Long Id){
        Optional<Use> use =U_repo.findById(Id);
        return use.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/useByTitle/{Title}")
    public Collection<Use> getUseByTitle(@PathVariable String Title){
        return U_repo.findAll().stream().filter(x ->x.getTitle().equals(Title)).collect(Collectors.toList());
    }
    @PostMapping("/newUse")
    public ResponseEntity<Use> addUse(@Valid @RequestBody Use use) throws URISyntaxException {
        log.info("request for adding new use ()",use);
        Use result = U_repo.save(use);
        return ResponseEntity.created(new URI("/newUse" + result.getId())).body(result);
    }
    @GetMapping("/useAllplant")
    public Set<Plant> getAllplants(Use use){return use.getPlantUsedIn();}
    @GetMapping("/useAllflowers")
    public Set<Flower> getAllflowers(Use use){return use.getFlowerUsedIn();}

    @PutMapping("/updateUse/{id}")

    public ResponseEntity<Use> updateUse(@Valid @RequestBody Use use, @PathVariable("id") long id) {
        log.info("Request for updating use {}", use);

        Optional<Use> useOptional = U_repo.findById(id);

        if (useOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Use U = useOptional.get();
        U.setId(use.getId());
        U.setGuide(use.getGuide());
        U.setFlowerUsedIn(use.getFlowerUsedIn());
        U.setPlantUsedIn(use.getPlantUsedIn());

        Use result = U_repo.save(U);

        return ResponseEntity.ok().body(result);

    }


}
