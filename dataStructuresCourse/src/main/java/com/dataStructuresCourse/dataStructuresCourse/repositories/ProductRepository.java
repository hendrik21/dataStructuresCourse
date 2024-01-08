package com.dataStructuresCourse.dataStructuresCourse.repositories;

import com.dataStructuresCourse.dataStructuresCourse.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

