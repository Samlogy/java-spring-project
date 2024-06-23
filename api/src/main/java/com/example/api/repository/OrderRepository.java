package com.example.api.repository;

import com.example.api.model.Orderr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orderr, Integer> {
//    Page<Orderr> findAll(Pageable pageable);


}