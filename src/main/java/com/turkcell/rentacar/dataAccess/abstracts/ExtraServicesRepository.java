package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.ExtraServices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtraServicesRepository extends JpaRepository<ExtraServices,Integer> {

}
