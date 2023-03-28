package com.stock.microservices.invmarketsvc.repository;

import com.stock.microservices.invmarketsvc.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends ReactiveCrudRepository<Stock, String> {
}
