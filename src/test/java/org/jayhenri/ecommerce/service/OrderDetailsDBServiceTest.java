package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.model.OrderDB;
import org.jayhenri.ecommerce.repository.OrderDBRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

/**
 * The type Order db service test.
 */
@ExtendWith(MockitoExtension.class)
class OrderDetailsDBServiceTest {

    /**
     * The Order db repository.
     */
    @Mock
    OrderDBRepository orderDBRepository;

    /**
     * The Argument captor.
     */
    @Captor
    ArgumentCaptor<OrderDB> argumentCaptor;

    /**
     * Add order to db.
     */
    @Test
    void addOrderToDB() {
        OrderDB orderDB = new OrderDB();
        OrderDBService testMe = new OrderDBService(orderDBRepository);

        testMe.addOrderToDB(orderDB);

        then(orderDBRepository).should().save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(orderDB);
    }
}