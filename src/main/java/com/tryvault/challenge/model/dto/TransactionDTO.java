package com.tryvault.challenge.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class TransactionDTO {
    @Id
    public Long id;
    @JsonProperty("customer_id")
    public String customerId;
    @JsonProperty("load_amount")
    public String loadAmount;
    public String time;
}
