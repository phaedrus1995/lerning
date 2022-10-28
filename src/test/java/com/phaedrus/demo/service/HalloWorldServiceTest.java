package com.phaedrus.demo.service;

import com.phaedrus.demo.entity.Locker;
import com.phaedrus.demo.entity.StoreResponse;
import com.phaedrus.demo.repository.HalloWorldRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HalloWorldServiceTest {

    @Mock
    private HalloWorldRepository halloWorldRepository;

    @Captor
    private ArgumentCaptor<Locker> lockerArgumentCaptor;

    @InjectMocks
    HalloWorldService halloWorldService;

    @Test
    void should_return_greet() {
        String result = halloWorldService.sayHallo("Benjamin");

        assertEquals("Hallo Benjamin!", result);
    }

    @Test
    void should_query_slots() {
        List<Locker> mockRepoResponse = new ArrayList<>();
        mockRepoResponse.add(new Locker(1, false, "001"));
        mockRepoResponse.add(new Locker(2, false, "002"));
        mockRepoResponse.add(new Locker(3, false, "003"));

        when(halloWorldRepository.findAllByStatus(any())).thenReturn(mockRepoResponse);
        Integer result = halloWorldService.query();

        assertEquals(3, result);
    }

    @Test
    void should_store_the_locker() {

//        Random randomNumberMock = Mockito.mock(Random.class, withSettings().withoutAnnotations());
//        when(randomNumberMock.nextInt()).thenReturn(12345);

        Locker firstMockLocker = new Locker(4, false, "some id");
        Locker secondMockLocker = new Locker(5, false, "some id");
        List<Locker> mockLockerList = new ArrayList<>();
        mockLockerList.add(firstMockLocker);
        mockLockerList.add(secondMockLocker);
        when(halloWorldRepository.findAllByStatus(false)).thenReturn(mockLockerList);
        Locker expectLocker = new Locker(4, true, "12345");
        when(halloWorldRepository.save(lockerArgumentCaptor.capture())).thenReturn(expectLocker);

        StoreResponse response = new StoreResponse(true, "12345");
        StoreResponse savedLocker = halloWorldService.store();
        assertEquals(response.message.length(), savedLocker.message.length());
        assertThat(lockerArgumentCaptor.getValue().getCustomerNumber().length()).isEqualTo(5);
    }

    @Test
    void should_not_store_if_there_are_no_empty_lockers() {
        List<Locker> mockLockerList = new ArrayList<>();
        when(halloWorldRepository.findAllByStatus(false)).thenReturn(mockLockerList);

        StoreResponse savedLocker = halloWorldService.store();
        verify(halloWorldRepository, never()).save(any());
        assertThat("no free locker").isEqualTo(savedLocker.message);
    }

    @Test
    void should_catch_the_exception_from_repo() {
        when(halloWorldRepository.findAllByStatus(false)).thenThrow(new RuntimeException());

        StoreResponse savedLocker = halloWorldService.store();
        assertThat(savedLocker.message).isEqualTo("Inner Error Occur");
    }

    @Test
    void should_release_resource() {
        when(halloWorldRepository.findByCustomerNumber("12345")).thenReturn(new Locker(3, true, "12345"));

        StoreResponse resultResponse = halloWorldService.release("12345");
        assertThat(new StoreResponse(true, "successful get your package")).isEqualToComparingFieldByField(resultResponse);
        verify(halloWorldRepository).save(new Locker(3, false, null));
    }

    @Test
    void should_return_error_message_when_error_occur_during_release_resource() {
        when(halloWorldRepository.findByCustomerNumber("12345")).thenThrow(new RuntimeException());

        StoreResponse resultResponse = halloWorldService.release("12345");
        assertThat(new StoreResponse(false, "Sorry, an error occurs during release")).isEqualToComparingFieldByField(resultResponse);
        verify(halloWorldRepository, never()).save(any());
    }

}