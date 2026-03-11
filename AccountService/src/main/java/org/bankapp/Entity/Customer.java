package org.bankapp.Entity;


import jakarta.persistence.*;


@Entity
public class Customer extends CommonValue {
    public Customer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name="customer_id")
    private Long customerId;
   // @Column(name="customer_name")
    private String customerName;
   // @Column(name="email")
    private String email;
   // @Column(name="mobile_number")
    private String mobileNumber;

    public Long getCustomerId() {
        return customerId;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

}
