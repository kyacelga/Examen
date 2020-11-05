package com.kevin.webflux.webflux.business_controllers;

import com.kevin.webflux.WebFlux.documents.Product;
import com.kevin.webflux.WebFlux.dtos.ProductDto;
import com.kevin.webflux.WebFlux.exceptions.NotFoundException;
import com.kevin.webflux.WebFlux.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ProductController {


    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<ResponseEntity> createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setCode(productDto.getCode());
        product.setName(productDto.getName());
        product.setType(productDto.getType());
        product.setUser(productDto.getUser());
        product.setActive(productDto.getActive());
        product.setData(productDto.getData());
        return this.productRepository.save(product).map(calback -> {
            return new ResponseEntity("\"product created\"", HttpStatus.CREATED);
        }).onErrorReturn(new ResponseEntity("\" product non't created\"", HttpStatus.NOT_ACCEPTABLE));
    }

    public Mono<ProductDto> findProductByCode(String code) {
        return this.productRepository.findById(code).switchIfEmpty(Mono.error(new NotFoundException(" product " + code)))
                .map(ProductDto::new);
    }

    public Flux<ProductDto> search() {
        return this.productRepository.findAll().map(ProductDto::new);
    }

    public Mono<ResponseEntity> editProduct(String code, ProductDto productDto) {
        Mono<Product> product = this.productRepository.findById(code).switchIfEmpty(Mono.error(new NotFoundException(" product " + code)))
                .map(productDb -> {
                    productDb.setName(productDto.getName());
                    productDb.setType(productDto.getType());
                    productDb.setUser(productDto.getUser());
                    productDb.setActive(productDto.getActive());
                    productDb.setData(productDto.getData());
                    return productDb;
                });
        return Mono.when(product).
                then(this.productRepository.save(product.block())
                        .map(
                                calback -> {
                                    return new ResponseEntity("\"product updated\"", HttpStatus.ACCEPTED);
                                }
                        )
                );
    }
}
