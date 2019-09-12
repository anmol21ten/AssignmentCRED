package com.cred.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoindeskDTO {

    @NonNull
    LocalDate date;

    @NonNull
    Double value;


}
