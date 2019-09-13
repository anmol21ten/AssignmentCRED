package com.cred.assignment.services;

import com.cred.assignment.dto.CoindeskDTO;
import com.cred.assignment.exchanges.MaximumGainRequest;
import com.cred.assignment.exchanges.MaximumGainResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MaximumGainServiceV1 implements MaximumGainService {

    public static final String COINDESK_API_ENDPOINT = "https://api.coindesk.com/v1/bpi/historical/close.json?currency=USD";


    @Override
    public MaximumGainResponse getMaximumGain(MaximumGainRequest maximumGainRequest) {

        MaximumGainResponse maximumGainResponse = null;

        //Checks if there is any problem with given request
        if(maximumGainRequest.getStart_date() != null && maximumGainRequest.getEnd_date() != null) {
            LocalDate startDate = LocalDate.parse(maximumGainRequest.getStart_date());
            LocalDate endDate = LocalDate.parse(maximumGainRequest.getEnd_date());
            if(!endDate.isAfter(startDate)){
                return maximumGainResponse;
            }
            //Function to get Data from CoinDesk API
            List<CoindeskDTO> dtoList = getData(startDate,endDate);
            //Function to Calculate Maximum Gain
            maximumGainResponse = calculateMaximumGain(dtoList);
        }
        return maximumGainResponse;
    }

    private MaximumGainResponse calculateMaximumGain(List<CoindeskDTO> dtoList) {
        CoindeskDTO buy = dtoList.get(0);
        CoindeskDTO sell = dtoList.get(0);
        CoindeskDTO min = dtoList.get(0);

        for(CoindeskDTO curr : dtoList) {
            if(curr.getValue() < min.getValue()) {
                min = curr;
            }
            if(curr.getValue() - min.getValue()  > sell.getValue() - buy.getValue()) {
                buy = min;
                sell = curr;
            }
        }

        MaximumGainResponse maximumGainResponse = new MaximumGainResponse
                (buy.getDate().toString(),sell.getDate().toString(),
                        sell.getValue()-buy.getValue());

        return maximumGainResponse;
    }

    private List<CoindeskDTO> getData(LocalDate startDate, LocalDate endDate){

        //Makes API Call
        List<CoindeskDTO> dtoList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder apiURL = new StringBuilder();
        apiURL.append(COINDESK_API_ENDPOINT);

        apiURL.append("&start=");
        apiURL.append(startDate.toString());

        apiURL.append("&end=");
        apiURL.append(endDate.toString());

        ResponseEntity<String> coindeskResponse = restTemplate.getForEntity(apiURL.toString(),String.class);

        ObjectMapper mapper = new ObjectMapper();
        //Iterates the response to create list
        JsonNode root = null;
        try {
            root = mapper.readTree(coindeskResponse.getBody());
            JsonNode bpi = root.path("bpi");
            Iterator<String> dateIterator = bpi.fieldNames();
            while(dateIterator.hasNext()) {
                String dateString = dateIterator.next();
                Double value = bpi.get(dateString).asDouble();
                dtoList.add(new CoindeskDTO(LocalDate.parse(dateString),value));
            }
        } catch(Exception e) {
        }

        return dtoList;
        }
}
