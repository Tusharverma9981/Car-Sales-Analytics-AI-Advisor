
package com.example.CarSales.service;

import com.example.CarSales.dto.YearCount;
import com.example.CarSales.entity.CarSales;
import com.example.CarSales.repository.CarSalesRepository;
import com.example.CarSales.dto.UploadSalesResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarSalesServiceImp implements CarSalesService {

    private final CarSalesRepository repository;

    public CarSalesServiceImp(CarSalesRepository repository) {
        this.repository = repository;
    }

    @Override
    public UploadSalesResponse uploadCsv(MultipartFile file) {

        List<CarSales> cars = new ArrayList<>();

        int failCount = 0;
        int totalRecords = 0;

        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     file.getInputStream(),
                                     StandardCharsets.UTF_8))) {

            // CSV Format
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader()                 // Header
                    .setSkipHeaderRecord(true)   // Skip header
                    .setIgnoreHeaderCase(true)   // Case-insensitive
                    .setTrim(true)
                    .build();

            CSVParser csvParser = CSVParser.parse(reader, csvFormat);

            for (CSVRecord record : csvParser) {

                totalRecords++;

                try {

                    String carNumber = record.get("Car Number");

                    boolean exists =
                            repository.existsByCarNumber(carNumber);

                    if (exists) {
                        failCount++;

                        System.out.println(
                                "Duplicate Data Found " + carNumber
                        );

                        continue;
                    }

                    CarSales carSales = new CarSales();

                    carSales.setCarNumber(record.get("Car Number"));
                    carSales.setBrand(record.get("Brand"));
                    carSales.setModel(record.get("Model"));
                    carSales.setColor(record.get("Color"));

                    carSales.setYear(
                            Integer.parseInt(record.get("Year"))
                    );

                    carSales.setDateOfPurchase(
                            LocalDate.parse(
                                    record.get("Date of Purchase"),
                                    DateTimeFormatter.ofPattern("dd-MM-yyyy")
                            )
                    );

                    carSales.setTimeOfPurchase(
                            LocalTime.parse(record.get("Time of Purchase"))
                    );

                    carSales.setPrice(
                            Long.parseLong(record.get("Price (Rs)"))
                    );

                    carSales.setMileage(
                            Double.parseDouble(record.get("Mileage (km/l)"))
                    );

                    carSales.setEngine(
                            Integer.parseInt(record.get("Engine (cc)"))
                    );

                    carSales.setFuelType(record.get("Fuel Type"));
                    carSales.setPaymentMode(record.get("Payment Mode"));
                    carSales.setState(record.get("State"));
                    carSales.setCity(record.get("City"));

                    carSales.setCustomerName(
                            record.get("Customer Name")
                    );

                    carSales.setContactNumber(
                            record.get("Contact Number")
                    );

                    carSales.setEmail(record.get("Email"));

                    carSales.setWarrantyPeriod(
                            Integer.parseInt(
                                    record.get("Warranty Period (years)")
                            )
                    );

                    cars.add(carSales);

                } catch (Exception e) {

                    failCount++;

                    System.out.println(
                            "Fail to Process Row: "
                                    + record.getRecordNumber()
                    );
                }
            }

            if (!cars.isEmpty()) {
                repository.saveAll(cars);
            }

        } catch (Exception exception) {

            throw new RuntimeException(
                    "Unable to Parse CSV: " + exception.getMessage()
            );
        }

        int successCount = totalRecords - failCount;

        return new UploadSalesResponse(
                totalRecords,
                successCount,
                failCount
        );
    }

    @Override
    public List<YearCount> getYearlyCount() {
        return repository.getYearlyCount();
    }
}
