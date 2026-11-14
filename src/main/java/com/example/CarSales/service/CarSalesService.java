package com.example.CarSales.service;

import com.example.CarSales.dto.UploadSalesResponse;
import com.example.CarSales.dto.YearCount;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarSalesService {

    UploadSalesResponse uploadCsv(MultipartFile file);

    List<YearCount> getYearlyCount();
}
