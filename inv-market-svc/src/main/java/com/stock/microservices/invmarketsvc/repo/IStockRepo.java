package com.stock.microservices.invmarketsvc.repo;

import com.stock.microservices.invmarketsvc.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRepo extends JpaRepository<Stock, String> {
}
