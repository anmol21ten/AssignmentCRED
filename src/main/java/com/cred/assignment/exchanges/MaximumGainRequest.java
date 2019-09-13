package com.cred.assignment.exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaximumGainRequest {

    @NonNull
    String start_date;

    @NonNull
    String end_date;
}
