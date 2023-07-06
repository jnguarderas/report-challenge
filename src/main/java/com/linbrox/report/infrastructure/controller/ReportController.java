package com.linbrox.report.infrastructure.controller;


import com.linbrox.report.application.service.SummarySaleService;
import com.linbrox.report.common.CryptoCurrencyEnum;
import com.linbrox.report.common.HyundaiModelEnum;
import com.linbrox.report.domain.model.ReportResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@RestController
@Api(tags = "Report")
public class ReportController {
    private final Logger logger = Logger.getLogger(ReportController.class.getName());

    private final SummarySaleService summarySaleService;

    public ReportController(SummarySaleService summarySaleService){
        this.summarySaleService = summarySaleService;
    }


    @ApiOperation(value = "Execute operations of transactions", notes = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Something bad happened")
    })
    @GetMapping("/report")
    public Mono<ReportResponse> create(@ApiParam(value = "Model")
                               @RequestParam HyundaiModelEnum model,
                                 @ApiParam(value = "CryptoCurrency")
                               @RequestParam CryptoCurrencyEnum cryptoCurrency,
                                 @ApiParam(value = "Date (dd-MM-yyyyy)")
                               @RequestParam String dateString){
        // Parse the dateString into a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
            var reportResponse = this.summarySaleService.retrieveDailySale(date, model.name(), cryptoCurrency.name());
            return Mono.just(reportResponse);
        } catch (ParseException e) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something is wrong with the request");
        }
    }
}
