package com.isg.soa.Projet.MedicinalTunisianPlants.Controllers;

import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Customer;
import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Flower;
import com.isg.soa.Projet.MedicinalTunisianPlants.Repositories.CustomerRpository;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<?> getCustomerById(@PathVariable("code") Long code) {
        Optional<Customer> customer = C_repo.findById(code);
        return customer.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/customerByName/{Name}")
    public Collection<Customer> getCustomerByName(@PathVariable String Name) {
        return C_repo.findAll().stream().filter(x -> x.getName().equals(Name)).collect(Collectors.toList());

    }

    private boolean isLoyalCustomer(Customer customer) {
        return customer.getType().equals("Loyal");
    }

    @GetMapping("/allLoyalCustomers")
    public Collection<Customer> allLoyalCustomer() {
        return C_repo.findAll().stream().filter(this::isLoyalCustomer).collect(Collectors.toList());
    }

    @PostMapping("/newCustomer")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) throws URISyntaxException {
        log.info("request for adding new customer ()", customer);
        Customer result = C_repo.save(customer);
        return ResponseEntity.created(new URI("/newCustomer" + result.getCode())).body(result);

    }


    @PutMapping("/updateCustomer/{code}")

    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer, @PathVariable("code") long code) {
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
        Customer result = C_repo.save(C1);
        return ResponseEntity.ok().body(result);

    }
    @DeleteMapping("/deleteCustomer/{code}")

    public Map<String, Boolean> deleteCustomer(@PathVariable Long code)
            throws ResourceNotFoundException {
        Customer C1 = C_repo.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this code : " + code));
        C_repo.delete(C1);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

