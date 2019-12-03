package com.isg.soa.Projet.MedicinalTunisianPlants.Repositories;

import com.isg.soa.Projet.MedicinalTunisianPlants.Models.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant,Long> {
}
