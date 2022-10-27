package com.phaedrus.demo.repository;

import com.phaedrus.demo.entity.Locker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HalloWorldRepository extends CrudRepository<Locker, Long> {

    List<Locker> findAllByStatus(Boolean status);

    Locker findByCustomerNumber(String customerNumber);

}
