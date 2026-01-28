package com.mr.adopciones.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mr.adopciones.models.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{
    List<Pet> findByCategoryAndAdoptedFalse(String category);
    
    List<Pet> findByAdoptedFalse();
}
