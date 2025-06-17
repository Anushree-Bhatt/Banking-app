package com.learn_springboot.banking_app.dto;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
//public class AccountDto {
//    private Long accountId;
//    private String accountHolderName;
//    private Double balance;
//}

public record AccountDto(Long accountId, String accountHolderName, Double balance) {
}
