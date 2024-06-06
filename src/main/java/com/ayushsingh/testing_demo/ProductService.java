package com.ayushsingh.testing_demo;

import java.util.List;

public interface ProductService {
  List<ListProductProjection> getAllProducts();

  Product createNewProduct(Product product);

  Boolean deleteProduct(String productToken);

  Product getProduct(String productToken);

  Product updateProduct(Product product);
}
