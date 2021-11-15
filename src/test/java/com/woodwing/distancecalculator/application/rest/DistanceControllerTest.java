package com.woodwing.distancecalculator.application.rest;

import com.woodwing.distancecalculator.application.request.CalculationRequest;
import com.woodwing.distancecalculator.application.response.CalculationResponse;
import com.woodwing.distancecalculator.domain.Distance;
import com.woodwing.distancecalculator.domain.UnitDistance;
import com.woodwing.distancecalculator.domain.service.DistanceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DistanceControllerTest {

    @InjectMocks
    private DistanceController sut;

    @Mock
    private DistanceService service;

    @Test
    void calculatesDistance() {
        Distance expectedDistance = Distance.builder().value(2d).unit(UnitDistance.YARDS).build();
        when(service.addDistances(any(CalculationRequest.class)))
                .thenReturn(CalculationResponse.builder().result(expectedDistance).build());

        Object response = sut.calculateDistance(1d, 1d, UnitDistance.YARDS, UnitDistance.YARDS, UnitDistance.YARDS);

        assertThat(response).isInstanceOf(ResponseEntity.class);
        assertThat(((ResponseEntity<CalculationResponse>) response).getBody().getResult()).isEqualTo(expectedDistance);
    }

    @Test
    void errorCalculatingDistance() {
        assertThrows(IllegalArgumentException.class,
                () -> sut.calculateDistance(1d, 1d, UnitDistance.valueOf("dummy-enum"), UnitDistance.YARDS, UnitDistance.YARDS));
    }

}