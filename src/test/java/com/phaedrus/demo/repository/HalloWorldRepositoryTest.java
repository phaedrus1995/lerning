package com.phaedrus.demo.repository;

import com.phaedrus.demo.entity.Locker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class HalloWorldRepositoryTest {
    @Autowired
    private HalloWorldRepository halloWorldRepository;

    @BeforeEach
    void initUseCase() {
        Locker firstLocker = new Locker(1, false, "001");
        Locker secondLocker = new Locker(2, false, "002");
        Locker thirdLocker = new Locker(3, true, "003");
        Locker fourthLocker = new Locker(4, false, "004");
        Locker fifthLocker = new Locker(5, true, "005");


        List<Locker> lockerList = Arrays.asList(firstLocker, secondLocker, thirdLocker, fourthLocker, fifthLocker);
        halloWorldRepository.saveAll(lockerList);
    }

    @Test
    void should_check_the_initial_state() {
        List<Locker> result = halloWorldRepository.findAllByStatus(true);
        System.out.println(result.size());
    }

//    @Test
//    void should_get_Locker_by_ids() {
//        List<Locker> result = halloWorldRepository.findByStatus(true);
//        assertEquals(result, result);
//    }

}