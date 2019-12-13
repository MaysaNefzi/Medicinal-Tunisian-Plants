package com.isg.soa.Projet.MedicinalTunisianPlants.Controllers;

import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Flower;
import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Order;
import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Plant;
import com.isg.soa.Projet.MedicinalTunisianPlants.Repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private final OrderRepository O_repo;
    public OrderController(OrderRepository o_repo) { O_repo = o_repo; }

    @GetMapping("/order/{code}")
    public ResponseEntity<?> getOrderById(@PathVariable("code") Long code){
        Optional<Order> Or =O_repo.findById(code);
        return Or.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/allOrderflowers")
    public Set<Flower> getAllorderflowers(Order order){return (order.getFlowers());}
    @GetMapping("/allOrderplants")
    public Set<Plant> getAllorderplants(Order order){return (order.getPlants());}

    @PostMapping("/newOrder")
    public ResponseEntity<Order> addOrder(@Valid @RequestBody Order order) throws URISyntaxException {
        log.info("request for adding new order ()",order);
        Order result = O_repo.save(order);
        return ResponseEntity.created(new URI("/newOrder" + result.getCode())).body(result);
    }
    @PutMapping("/updateOrder/{code}")

    public ResponseEntity<Order> updateOrder(@Valid @RequestBody Order order, @PathVariable("code") long code) {
        log.info("Request for updating Order {}", order);

        Optional<Order> orderOptional = O_repo.findById(code);

        if (orderOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Order O = orderOptional.get();
        O.setCode(order.getCode());
        O.setDate(order.getDate());
        O.setFlowers(order.getFlowers());
        O.setPlants(order.getPlants());

        Order result = O_repo.save(O);

        return ResponseEntity.ok().body(result);

    }

}
