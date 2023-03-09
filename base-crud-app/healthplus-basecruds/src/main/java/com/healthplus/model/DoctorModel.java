package com.healthplus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorModel {
    private long id;
    private String firstName;
    private String lastName;
    private String emailId;
}
