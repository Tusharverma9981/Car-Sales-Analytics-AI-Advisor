package com.example.CarSales.controller;

import com.example.CarSales.commons.response.ApiResponse;
import com.example.CarSales.dto.UploadSalesResponse;
import com.example.CarSales.dto.YearCount;
import com.example.CarSales.service.CarSalesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/car-sales")
public class CarSalesController {


    private final CarSalesService salesService;

    public CarSalesController(CarSalesService salesService){
        this.salesService = salesService;
    }


    @PostMapping("/upload-csv")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file){

        //if file not found
        if(file.isEmpty()){
            //res

            UploadSalesResponse response =  new UploadSalesResponse(0,0,0);

            ApiResponse<UploadSalesResponse> apiResponse =   new ApiResponse<UploadSalesResponse>(false,
                    "file not found",response,
                    HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<ApiResponse<UploadSalesResponse>>(apiResponse,HttpStatus.BAD_REQUEST);
        }

        //res
        UploadSalesResponse response =  salesService.uploadCsv(file);
        ApiResponse<UploadSalesResponse> apiResponse = getApiResponse(response);

        return ResponseEntity.ok(apiResponse);
    }
    private  static ApiResponse<UploadSalesResponse> getApiResponse(UploadSalesResponse response){
       String message;
       boolean success;

       if (response.getFailedCount()==0){
           message = "All Records Uploaded succesfully";
           success = true;
       } else if (response.getSuccessCount()==0) {
           message = "All Records Failed to upload";
           success = false;
       }
       else{
           message = "Uploaded with some errors "+response.getFailedCount()+" rows failed";
           success = false;
       }


        return  new ApiResponse<UploadSalesResponse>(success,message,response,HttpStatus.OK.value());
    }

    @GetMapping("/yearly-count")
    public ResponseEntity<ApiResponse<List<YearCount>>> yearlyCount() {

        List<YearCount> carsCount = salesService.getYearlyCount();

        ApiResponse<List<YearCount>> response =
                new ApiResponse<List<YearCount>>(
                        true,
                        "Data Read Successfully",
                        carsCount,
                        HttpStatus.OK.value()
                );

        return ResponseEntity.ok(response);
    }

}
