package com.ayushsingh.testing_demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductControllerTest {

  private static final String BASE_URL = "http://localhost:8080/api/v1/product";
  private static Long productId;
  private static String productToken;

  @BeforeAll
  public static void setUp() {
    RestAssured.baseURI = BASE_URL;
  }

  @Test
  @Order(1)
  void testCreateProduct() {
    Product newProduct = new Product();
    newProduct.setProductName("Product 2301" + System.currentTimeMillis());
    newProduct.setStockQuantity(675L);

    Response response = given()
      .contentType(ContentType.JSON)
      .body(newProduct)
      .when()
      .post("/new")
      .then()
      .statusCode(201)
      .extract()
      .response();

    //- Extract productId and productToken from the response and store them
    productId = response.jsonPath().getLong("data.productId");
    productToken = response.jsonPath().getString("data.productToken");
  }

  @Test
  @Order(2)
  void testUpdateProduct() {
    Product updateProduct = new Product();
    updateProduct.setProductId(productId);
    updateProduct.setProductToken(productToken);
    updateProduct.setProductName("Product 265" + System.currentTimeMillis());
    updateProduct.setStockQuantity(600L);

    Response response = given()
      .contentType(ContentType.JSON)
      .body(updateProduct)
      .when()
      .patch("/change")
      .then()
      .statusCode(200)
      .extract()
      .response();

    String productName = response.jsonPath().getString("data.productName");
    Long stockQuantity = response.jsonPath().getLong("data.stockQuantity");
    assertEquals(updateProduct.getProductName(), productName);
    assertEquals(updateProduct.getStockQuantity(), stockQuantity);
    assertEquals(productId, response.jsonPath().getLong("data.productId"));
    assertEquals(
      productToken,
      response.jsonPath().getString("data.productToken")
    );
  }

  @Test
  @Order(3)
  void testDeleteProduct() {
    Response response = given()
      .when()
      .delete("/delete?id=" + productId)
      .then()
      .statusCode(200)
      .extract()
      .response();

    Boolean res = response.jsonPath().getBoolean("data");
    assertEquals(true, res);
  }
}
