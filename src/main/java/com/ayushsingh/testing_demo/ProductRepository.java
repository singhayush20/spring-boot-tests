package com.ayushsingh.testing_demo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query("SELECT p FROM Product p WHERE p.productToken = ?1")
  Optional<Product> findByProductToken(String productToken);

  @Query(
    """
    select
    p.productToken as productToken,
    p.productName as productName
    from Product p
    """
  )
  List<ListProductProjection> getAllProducts();


    @Modifying
    @Query("DELETE FROM Product p WHERE p.productToken = ?1")
    void deleteByProductToken(String productToken);

}
