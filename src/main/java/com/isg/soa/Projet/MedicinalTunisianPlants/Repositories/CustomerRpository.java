package com.isg.soa.Projet.MedicinalTunisianPlants.Repositories;

import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRpository extends JpaRepository<Customer,Long> {
}
