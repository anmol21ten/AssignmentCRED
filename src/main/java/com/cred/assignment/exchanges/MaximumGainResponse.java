package com.cred.assignment.exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaximumGainResponse {

    @NonNull
    String buy_date;

    @NonNull
    String sell_date;

    @NonNull
    Double maximum_gain;
}
