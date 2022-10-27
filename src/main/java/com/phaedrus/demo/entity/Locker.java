package com.phaedrus.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
public class Locker {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer id;

    public Boolean status;
    @Column(name = "customer_number")
    public String customerNumber;

    public Locker(){}

    public Locker(int i, boolean b, String s) {
        this.id = i;
        this.status = b;
        this.customerNumber = s;
    }

    @Override
    public String toString() {
        return String.format(
                "Locker[id=%s, status='%b', customerNumber='%s']",
                id, status, customerNumber);
    }


}
