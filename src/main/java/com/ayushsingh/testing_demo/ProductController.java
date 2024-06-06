package com.ayushsingh.testing_demo;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/all")
  public ResponseEntity<ApiResponse<List<ListProductProjection>>> getAllProducts() {
    return new ResponseEntity<>(
      new ApiResponse<>(productService.getAllProducts()),
      HttpStatus.OK
    );
  }

  @PostMapping("/new")
  public ResponseEntity<ApiResponse<Product>> createNewProduct(
    @RequestBody Product product
  ) {
    Product p = productService.createNewProduct(product);
    return new ResponseEntity<>(new ApiResponse<>(p), HttpStatus.CREATED);
  }

  @PatchMapping("/change")
  public ResponseEntity<ApiResponse<Product>> updateProduct(
    @RequestBody Product product
  ) {
    Product p = productService.updateProduct(product);
    return new ResponseEntity<>(new ApiResponse<>(p), HttpStatus.OK);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<ApiResponse<Boolean>> deleteProduct(
    @RequestParam String id
  ) {
    Boolean res = productService.deleteProduct(id);
    return new ResponseEntity<>(new ApiResponse<>(res), HttpStatus.OK);
  }

  @GetMapping("/id")
  public ResponseEntity<ApiResponse<Product>> getMethodName(
    @RequestParam String id
  ) {
    return new ResponseEntity<>(
      new ApiResponse<>(this.productService.getProduct(id)),
      HttpStatus.OK
    );
  }
}
