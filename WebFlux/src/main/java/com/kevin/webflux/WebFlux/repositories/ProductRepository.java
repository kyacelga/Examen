package com.rafael.webflux.webflux.repositories;

import com.rafael.webflux.WebFlux.documents.Product;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface ProductRepository  extends ReactiveSortingRepository<Product,String> {
}
