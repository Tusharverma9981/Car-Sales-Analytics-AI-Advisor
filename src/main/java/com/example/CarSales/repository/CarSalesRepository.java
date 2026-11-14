package com.example.CarSales.repository;

import com.example.CarSales.dto.YearCount;
import com.example.CarSales.entity.CarSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarSalesRepository extends JpaRepository<CarSales,Long> {

    boolean existsByCarNumber(String carNumber);

    @Query("""
        SELECT new com.codehunt.carsales.dto.YearlyCountDto(c.year, count(c))
        FROM CarSales c
        GROUP BY c.year
        ORDER BY c.year
        """)
    List<YearCount> getYearlyCount();
}
