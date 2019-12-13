package com.isg.soa.Projet.MedicinalTunisianPlants.Controllers;

import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Product;
import com.isg.soa.Projet.MedicinalTunisianPlants.Repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private final ProductRepository Pr_repo;

    public ProductController(ProductRepository p_repo) {
        Pr_repo = p_repo;
    }
    @GetMapping("/allProducts")
    public Collection<Product> allProducts(){
        return Pr_repo.findAll();
    }
    @DeleteMapping("/removeProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        log.info("request for removing product {}", id);
        Pr_repo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/removeProductByName/{Name}")
    public ResponseEntity<?> deleteProductByName(@PathVariable("Name") String Name) {
        log.info("Request for removing product {}", Name);
        List<Product> lstproducts = Pr_repo.findAll().stream()
                .filter(x -> x.getName().equals(Name))
                .collect(Collectors.toList());

        for (Product pr : lstproducts)
            Pr_repo.deleteById(pr.getId());
        return ResponseEntity.ok().build();

    }

}
