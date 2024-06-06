package com.ayushsingh.testing_demo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ecom_product")
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  @Column(name = "product_token", nullable = false, unique = true)
  private String productToken;

  @Column(name = "product_name", nullable = false, unique = true)
  private String productName;

  @Column(name = "stock_quantity", nullable = false)
  private Long stockQuantity;

  @PrePersist
  public void prePersist() {
    productToken = UUID.randomUUID().toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(productToken, product.productToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productToken);
  }

  @Override
  public String toString() {
    return "Product [productId=" + productId + ", productToken=" + productToken + ", productName=" + productName
        + ", stockQuantity=" + stockQuantity + "]";
  }

  
}
