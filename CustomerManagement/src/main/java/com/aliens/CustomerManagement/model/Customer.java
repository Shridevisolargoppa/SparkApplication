package com.aliens.CustomerManagement.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "Customer")
public class Customer
{
    @Id
    String _id;
    int customerId;
    String name;
    String email;
    long phone;
    String address;
    String companyName;
    String industryType;
    String customerStatus;
    String accountManager;
    LocalDateTime audit;

    public Customer(int customerId, String name, String email, long phone, String address, String companyName, String industryType, String customerStatus, String accountManager) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.companyName = companyName;
        this.industryType = industryType;
        this.customerStatus = customerStatus;
        this.accountManager = accountManager;
        this.audit = LocalDateTime.now();
    }

    public void setAudit(LocalDateTime now) {
    }

    public void setCustomerId(int customerId) {
    }
}
