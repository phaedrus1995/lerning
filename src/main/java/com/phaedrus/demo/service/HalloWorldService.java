package com.phaedrus.demo.service;

import com.phaedrus.demo.entity.Locker;
import com.phaedrus.demo.entity.StoreResponse;
import com.phaedrus.demo.repository.HalloWorldRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
public class HalloWorldService {
    private final HalloWorldRepository halloWorldRepository;

    public String sayHallo(String name) {
        return String.format("Hallo %s!", name);
    }

    public Integer query() {
        List<Locker> emptyLockers = halloWorldRepository.findAllByStatus(false);
        return emptyLockers.size();
    }

    public StoreResponse store() {
        int customerNumber = new Random().nextInt(90000) + 10000;
        try {
        List<Locker> freeLocker = halloWorldRepository.findAllByStatus(false);
        if(freeLocker.size() == 0) {
            return new StoreResponse(false, "no free locker");
        }
        Integer targetId = freeLocker.get(0).getId();
        Locker newLockerStatus = new Locker(targetId, true, Integer.toString(customerNumber));
        Locker savedLocker = halloWorldRepository.save(newLockerStatus);
        return new StoreResponse(true, savedLocker.customerNumber);
        } catch (Exception error) {
            return new StoreResponse(false, "Inner Error Occur");
        }
    }

    public StoreResponse release(String customerNumber) {
        try{
            Integer targetLockerId = halloWorldRepository.findByCustomerNumber(customerNumber).getId();
            System.out.println("targetLockerId");
            System.out.println(targetLockerId);
            Locker result = halloWorldRepository.save(new Locker(targetLockerId, false, null));
            System.out.println("result");
            System.out.println(result);
            return new StoreResponse(true, "successful get your package");
        } catch (Exception error) {
            return new StoreResponse(false, "Sorry, an error occurs during release");
        }


    }
}
