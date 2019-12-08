package com.isg.soa.Projet.MedicinalTunisianPlants.Controllers;

import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Customer;
import com.isg.soa.Projet.MedicinalTunisianPlants.Repositories.CustomerRpository;
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
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private final CustomerRpository C_repo;

    public CustomerController(CustomerRpository c_repo) {
        C_repo = c_repo;
    }

    @GetMapping("/allcustomers")
    public Collection<Customer> allCustomers() {
        return C_repo.findAll();
       }
    @GetMapping("/customer/{code}")
    public ResponseEntity<?> getCustomerById(@PathVariable("code") Long code){ // variable simple n3adeha fil path variable kbira ojet par exp tet3ada fil body
        Optional<Customer> customer =C_repo.findById(code);//optional si le id 8alet optional permet de verfier si retour de type Toy ou non(vide)
        return customer.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        //map : transormation   toy iwali responseEntity
    }
    @GetMapping("/customerByName/{Name}")
    public Collection<Customer> getCustomerByName(@PathVariable String Name){
        return C_repo.findAll().stream().filter(x ->x.getName().equals(Name)).collect(Collectors.toList());//retourner les toy que leurs nom
        // contient var name (.collec(collectors.tolist()) : ty de routour le convertir de stream to list
    }

    private boolean isLoyalCustomer(Customer customer) {
        return customer.getType().equals("Loyal");
    }
    @GetMapping("/allLoyalCustomers") // "  : chemin d'acces dans le url
    public Collection<Customer> allLoyalCustomres(){
        return C_repo.findAll().stream().filter(this::isLoyalCustomer).collect(Collectors.toList());
        // :: cad pour chqu'un appliquer  appliquer isEle...
        //filter -> choisir les client  qui sont fideles
    }

    @PostMapping("/newCustomer")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) throws URISyntaxException { // n3adi objet fil body
        log.info("request for adding new customer ()",customer);
        Customer result = C_repo.save(customer);
        return ResponseEntity.created(new URI("/newCustomer" + result.getCode())).body(result);
        // Entity = trame  : contient head + body .

    }


    @PutMapping("/updateCustomer/{code}")

    public ResponseEntity<Customer> smupdateCustomer(@Valid @RequestBody Customer customer, @PathVariable("code") long code) {
        log.info("Request for updating new Customer {}", customer);

        Optional<Customer> customerOptional = C_repo.findById(code);

        if (customerOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Customer C1 = customerOptional.get();
        C1.setCode(code);
        C1.setName(customer.getName());
        C1.setType(customer.getType());
        C1.setPhone(customer.getPhone());
        C1.setAdress(customer.getAdress());
        C1.setCoeff_Fid(customer.getCoeff_Fid());
        C1.setEmail(customer.getEmail());
        C1.setLogin(customer.getLogin());
        C1.setPassword(customer.getPassword());
        C1.setProfile(customer.getProfile());
        // Fdidelite
        C1.Fidelite(C1.getCoeff_Fid());
        Customer result = C_repo.save(C1);

        return ResponseEntity.ok().body(result);
    }

}

