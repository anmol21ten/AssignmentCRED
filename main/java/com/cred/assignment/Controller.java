package com.cred.assignment;

import com.cred.assignment.exchanges.MaximumGainRequest;
import com.cred.assignment.exchanges.MaximumGainResponse;
import com.cred.assignment.services.MaximumGainService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping(Controller.API_ENDPOINT)
public class Controller {

    public static final String API_ENDPOINT = "/v1";
    public static final String API_CALCULATE = "/calculate";

    @Autowired
    MaximumGainService maximumGainService;


    //Controller for Calculate API
    @PostMapping(API_CALCULATE)
    public ResponseEntity<MaximumGainResponse> calculateMaximumGain
            (@RequestBody MaximumGainRequest maximumGainRequest){

        MaximumGainResponse maximumGainResponse;
        try {
            maximumGainResponse = maximumGainService.getMaximumGain(maximumGainRequest);
            //Checks if Response is not correct that means bad request
            if(maximumGainResponse != null) {
                return ResponseEntity.ok().body(maximumGainResponse);
            } else {
                log.info("Bad Request Encountered !");
                return ResponseEntity.badRequest().body(maximumGainResponse);
            }
        } catch (Exception e) {
            maximumGainResponse = new MaximumGainResponse();
            log.info("Bad Request Encountered !");
            return ResponseEntity.badRequest().body(maximumGainResponse);
        }
    }

}
