# 405MethodNotAllowed

This tool creates a function signature for a SpringBoot ReST enpoint from your command line. Below are some examples of how this tool works:

### Input:

>HTTP Method: GET<br>
>URI: api/v1/products/{productId}/stuff?page=page&size=size<br>
>Model: None<br>
>Name: getAllProducts<br>
>Return: List<Product><br>  
  
### Output:

>@GetMapping("api/v1/products/{productId}/stuff")  //?page=page&size=size<br>
>public List<Product> getAllProducts(@PathVariable productID, @RequestParam int page, @RequestParam int size) {<br>

  
### Input:

>HTTP Method: POST<br>
>URI: api/v1/products/<br>
>Model: Product<br>
>Name: addProduct<br>
>Return: Product<br>

### Output:

>@PostMapping("api/v1/products/")<br>
>public Product getAllProducts(@RequestBody Product product) {<br>
