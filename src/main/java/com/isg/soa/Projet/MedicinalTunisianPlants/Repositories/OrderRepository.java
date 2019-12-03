package com.isg.soa.Projet.MedicinalTunisianPlants.Repositories;

import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
