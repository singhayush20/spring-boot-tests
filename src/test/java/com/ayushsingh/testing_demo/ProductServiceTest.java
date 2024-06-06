package com.ayushsingh.testing_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private ProductServiceImpl productService;

  @Test
  public void saveProduct() {
    Product actualProduct = new Product();
    long code = System.currentTimeMillis();
    actualProduct.setProductName("Product" + code);
    actualProduct.setStockQuantity(222L);

    // Manually invoke prePersist method to set productToken
    actualProduct.prePersist();

    Product expectedProduct = new Product();
    expectedProduct.setProductId(1L); // Mocked ID assignment
    expectedProduct.setProductToken(actualProduct.getProductToken());
    expectedProduct.setProductName("Product" + code);
    expectedProduct.setStockQuantity(222L);

    when(productRepository.save(actualProduct)).thenReturn(expectedProduct);
    Product savedProduct = productService.createNewProduct(actualProduct);

    verify(productRepository, times(1)).save(actualProduct);
    System.out.println("Actual: " + actualProduct.toString());
    System.out.println("Expected: " + expectedProduct.toString());
    System.out.println("Saved: " + savedProduct.toString());

    // Check that the IDs and tokens are set and equal
    assertEquals(expectedProduct.getProductId(), savedProduct.getProductId());
    assertEquals(
      expectedProduct.getProductToken(),
      savedProduct.getProductToken()
    );
  }

  @Test
  public void deleteProduct() {
    String productToken = "sampleToken";

    Boolean result = productService.deleteProduct(productToken);

    verify(productRepository, times(1)).deleteByProductToken(productToken);
    assertEquals(true, result);
  }

  @Test
  public void updateProduct() {
    Product existingProduct = new Product();
    existingProduct.setProductId(1L);
    existingProduct.setProductToken("existingToken");
    existingProduct.setProductName("Existing Product");
    existingProduct.setStockQuantity(100L);

    Product updatedProduct = new Product();
    updatedProduct.setProductId(1L);
    updatedProduct.setProductToken("existingToken");
    updatedProduct.setProductName("Updated Product");
    updatedProduct.setStockQuantity(150L);

    when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

    Product result = productService.updateProduct(existingProduct);

    verify(productRepository, times(1)).save(existingProduct);
    assertEquals(updatedProduct.getProductName(), result.getProductName());
    assertEquals(updatedProduct.getStockQuantity(), result.getStockQuantity());
  }

  @Test
  public void getProduct() {
    String productToken = "sampleToken";

    Product expectedProduct = new Product();
    expectedProduct.setProductId(1L);
    expectedProduct.setProductToken(productToken);
    expectedProduct.setProductName("Sample Product");
    expectedProduct.setStockQuantity(100L);

    when(productRepository.findByProductToken(productToken))
      .thenReturn(Optional.of(expectedProduct));

    Product result = productService.getProduct(productToken);

    verify(productRepository, times(1)).findByProductToken(productToken);
    assertEquals(expectedProduct.getProductName(), result.getProductName());
    assertEquals(expectedProduct.getStockQuantity(), result.getStockQuantity());
  }
}
