package com.tryvault.challenge.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonPropertyOrder({"id", "customerId", "accepted"})
public class TransactionResultDTO {
    public String id;
    @JsonProperty("customer_id")
    public String customerId;
    public boolean accepted;
}
