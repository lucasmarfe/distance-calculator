package com.woodwing.distancecalculator.domain.service;

import com.woodwing.distancecalculator.application.request.CalculationRequest;
import com.woodwing.distancecalculator.application.response.CalculationResponse;
import com.woodwing.distancecalculator.domain.Distance;
import com.woodwing.distancecalculator.domain.UnitDistance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class DistanceServiceTest {

    @InjectMocks
    private DistanceService service;

    @ParameterizedTest
    @MethodSource("distancesSource")
    void calculateDistances(double distanceA, UnitDistance distanceAUnit, double distanceB, UnitDistance distanceBUnit, UnitDistance desiredUnit, String expectedValue) {
        var request = CalculationRequest.builder()
                .distanceA(Distance.builder().value(distanceA).unit(distanceAUnit).build())
                .distanceB(Distance.builder().value(distanceB).unit(distanceBUnit).build())
                .resultUnitDistance(desiredUnit)
                .build();

        var result = service.addDistances(request);

        assertThat(format(result.getResult().getValue())).isEqualTo(expectedValue);
        assertThat(result.getResult().getUnit()).isEqualTo(desiredUnit);
    }

    private static Stream<Arguments> distancesSource() {
        return Stream.of(
                Arguments.of(2.2d, UnitDistance.METERS, 3.3d, UnitDistance.METERS, UnitDistance.METERS, "5.500"),
                Arguments.of(2.3d, UnitDistance.YARDS, 3.2d, UnitDistance.YARDS, UnitDistance.YARDS, "5.500"),
                Arguments.of(2d, UnitDistance.YARDS, 3d, UnitDistance.YARDS, UnitDistance.METERS, "4.572"),
                Arguments.of(2.2d, UnitDistance.METERS, 3.3d, UnitDistance.METERS, UnitDistance.YARDS, "6.015"),
                Arguments.of(2.2d, UnitDistance.METERS, 3.3d, UnitDistance.YARDS, UnitDistance.YARDS, "5.706"),
                Arguments.of(2.2d, UnitDistance.YARDS, 3.3d, UnitDistance.METERS, UnitDistance.YARDS, "5.809"),
                Arguments.of(2.2d, UnitDistance.METERS, 3.3d, UnitDistance.YARDS, UnitDistance.METERS, "5.218"),
                Arguments.of(2.2d, UnitDistance.YARDS, 3.3d, UnitDistance.METERS, UnitDistance.METERS, "5.312"));
    }

    @Test
    void calculateDistanceOverflow() {
        CalculationRequest request = CalculationRequest.builder()
                .distanceA(Distance.builder().value(Double.MAX_VALUE).unit(UnitDistance.METERS).build())
                .distanceB(Distance.builder().value(Double.MAX_VALUE).unit(UnitDistance.METERS).build())
                .resultUnitDistance(UnitDistance.METERS)
                .build();

        CalculationResponse result = service.addDistances(request);

        assertTrue(result.getResult().getValue().isInfinite());
        assertThat(result.getResult().getUnit()).isEqualTo(UnitDistance.METERS);
    }

    public static String format(double num) {
        DecimalFormatSymbols decimalSymbols = DecimalFormatSymbols.getInstance();
        decimalSymbols.setDecimalSeparator('.');
        return new DecimalFormat("0.000", decimalSymbols).format(num);
    }
    
}