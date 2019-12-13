package com.isg.soa.Projet.MedicinalTunisianPlants.Controllers;

import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Admin;
import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Flower;
import com.isg.soa.Projet.MedicinalTunisianPlants.Repositories.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final Logger log = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private final AdminRepository A_repo;
    public AdminController(AdminRepository a_repo) {
        A_repo = a_repo;
    }
    @GetMapping("/alladmins")
    public Collection<Admin> alladmins() {
        return A_repo.findAll();
    }
    @PostMapping("/newAdmin")
    public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin admin) throws URISyntaxException {
        log.info("request for adding new admin()", admin);
        Admin result = A_repo.save(admin);
        return ResponseEntity.created(new URI("/newAdmin" + result.getId())).body(result);
    }
    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<Admin> updateCustomer(@Valid @RequestBody Admin admin, @PathVariable("id") long id) {
        log.info("Request for updating admin {}", admin);

        Optional<Admin> adminOptional = A_repo.findById(id);

        if (adminOptional.isEmpty())
            return ResponseEntity.notFound().build();
        Admin A1 = adminOptional.get();
        A1.setId(id);
        A1.setName(admin.getName());
        A1.setFirstName(admin.getFirstName());
        A1.setLogin(admin.getLogin());
        A1.setPassword(admin.getPassword());
        Admin result = A_repo.save(A1);
        return ResponseEntity.ok().body(result);

    }
    @DeleteMapping("/deleteAdmin/{id}")

    public Map<String, Boolean> deleteAdmin(@PathVariable Long id)
            throws ResourceNotFoundException {
        Admin admin = A_repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found for this id : " + id));
        A_repo.delete(admin);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
