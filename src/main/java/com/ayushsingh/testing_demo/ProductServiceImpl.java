package com.ayushsingh.testing_demo;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public List<ListProductProjection> getAllProducts() {
    return productRepository.getAllProducts();
  }

  @Override
  public Product createNewProduct(Product product) {
    return productRepository.save(product);
  }

  @Transactional
  @Override
  public Boolean deleteProduct(String productToken) {
    this.productRepository.deleteByProductToken(productToken);
    return true;
  }

  @Override
  public Product getProduct(String productToken) {
    return productRepository.findByProductToken(productToken).get();
  }

  @Override
  public Product updateProduct(Product product) {
    return productRepository.save(product);
  }
}
