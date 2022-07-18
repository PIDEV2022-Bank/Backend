package com.esprit.pidev2022.Dto;


import lombok.Data;

@Data
public class WithdrawaltDto {

    private String accountSource;
    private double amount;
    private String description;
}
