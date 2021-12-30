﻿# 405MethodNotAllowed

This tool creates a function signature for a SpringBoot ReST enpoint from your command line. Below are some examples of how this tool works:

##Input:

<code>
HTTP Method: GET
URI: api/v1/products/{productId}/stuff?page=page&size=size
Model: None
Name: getAllProducts
Return: List<Product>
</code>  
  
##Output:

@GetMapping("api/v1/products/{productId}/stuff")  //?page=page&size=size
public List<Product> getAllProducts(@PathVariable productID, @RequestParam int page, @RequestParam int size) {

  
##Input:

HTTP Method: POST
URI: api/v1/products/
Model: Product
Name: addProduct
Return: Product

##Output:

@PostMapping("api/v1/products/")
public Product getAllProducts(@RequestBody Product product) {
