package com.esprit.pidev2022.Dto;


import lombok.Data;

@Data
public class TransferDto {
    private String accountSource;
    private String accountDestination;
    private double amount;
    private String description;
}
