package com.woodwing.distancecalculator.domain.service;

import com.woodwing.distancecalculator.application.request.CalculationRequest;
import com.woodwing.distancecalculator.application.response.CalculationResponse;
import com.woodwing.distancecalculator.domain.Distance;
import com.woodwing.distancecalculator.domain.UnitDistance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class DistanceServiceTest {

    @InjectMocks
    private DistanceService service;

    @Test
    void calculateDistanceMetersNoConversion() {
        CalculationRequest request = CalculationRequest.builder()
                .distanceA(Distance.builder().value(2.2d).unit(UnitDistance.METERS).build())
                .distanceB(Distance.builder().value(3.3d).unit(UnitDistance.METERS).build())
                .resultUnitDistance(UnitDistance.METERS)
                .build();

        CalculationResponse result = service.addDistances(request);

        assertThat(result.getResult().getValue()).isEqualTo(5.5d);
        assertThat(result.getResult().getUnit()).isEqualTo(UnitDistance.METERS);
    }

    @Test
    void calculateDistanceOverflow() {
        CalculationRequest request = CalculationRequest.builder()
                .distanceA(Distance.builder().value(Double.MAX_VALUE).unit(UnitDistance.METERS).build())
                .distanceB(Distance.builder().value(Double.MAX_VALUE).unit(UnitDistance.METERS).build())
                .resultUnitDistance(UnitDistance.METERS)
                .build();

        CalculationResponse result = service.addDistances(request);

        assertThat(result.getResult().getValue()).satisfies(d -> d.isInfinite());
        assertThat(result.getResult().getUnit()).isEqualTo(UnitDistance.METERS);
    }

    @Test
    void calculateDistanceYardsNoConversion() {
        CalculationRequest request = CalculationRequest.builder()
                .distanceA(Distance.builder().value(2.3d).unit(UnitDistance.YARDS).build())
                .distanceB(Distance.builder().value(3.2d).unit(UnitDistance.YARDS).build())
                .resultUnitDistance(UnitDistance.YARDS)
                .build();

        CalculationResponse result = service.addDistances(request);

        assertThat(result.getResult().getValue()).isEqualTo(5.5d);
        assertThat(result.getResult().getUnit()).isEqualTo(UnitDistance.YARDS);
    }

    @Test
    void sumDistanceAYardsDistanceBYardsToMeters() {
        CalculationRequest request = CalculationRequest.builder()
                .distanceA(Distance.builder().value(2d).unit(UnitDistance.YARDS).build())
                .distanceB(Distance.builder().value(3d).unit(UnitDistance.YARDS).build())
                .resultUnitDistance(UnitDistance.METERS)
                .build();

        CalculationResponse result = service.addDistances(request);

        assertThat(result.getResult().getValue()).isEqualTo(4.572d);
        assertThat(result.getResult().getUnit()).isEqualTo(UnitDistance.METERS);
    }

    @Test
    void sumDistanceAMetersDistanceBMetersToYards() {
        CalculationRequest request = CalculationRequest.builder()
                .distanceA(Distance.builder().value(2.2d).unit(UnitDistance.METERS).build())
                .distanceB(Distance.builder().value(3.3d).unit(UnitDistance.METERS).build())
                .resultUnitDistance(UnitDistance.YARDS)
                .build();

        CalculationResponse result = service.addDistances(request);


        assertThat(format(result.getResult().getValue())).isEqualTo("6.015");
        assertThat(result.getResult().getUnit()).isEqualTo(UnitDistance.YARDS);
    }

    @Test
    void sumDistanceAMetersDistanceBYardsToYards() {
        CalculationRequest request = CalculationRequest.builder()
                .distanceA(Distance.builder().value(5.3d).unit(UnitDistance.METERS).build())
                .distanceB(Distance.builder().value(3.3d).unit(UnitDistance.YARDS).build())
                .resultUnitDistance(UnitDistance.YARDS)
                .build();

        CalculationResponse result = service.addDistances(request);

        assertThat(format(result.getResult().getValue())).isEqualTo("9.096");
        assertThat(result.getResult().getUnit()).isEqualTo(UnitDistance.YARDS);
    }

    @Test
    void sumDistanceAYardsDistanceBMetersToYards() {
        CalculationRequest request = CalculationRequest.builder()
                .distanceA(Distance.builder().value(2.2d).unit(UnitDistance.YARDS).build())
                .distanceB(Distance.builder().value(3.3d).unit(UnitDistance.METERS).build())
                .resultUnitDistance(UnitDistance.YARDS)
                .build();

        CalculationResponse result = service.addDistances(request);


        assertThat(format(result.getResult().getValue())).isEqualTo("5.809");
        assertThat(result.getResult().getUnit()).isEqualTo(UnitDistance.YARDS);
    }

    @Test
    void sumDistanceAMetersDistanceBYardsToMeters() {
        CalculationRequest request = CalculationRequest.builder()
                .distanceA(Distance.builder().value(2.2d).unit(UnitDistance.METERS).build())
                .distanceB(Distance.builder().value(3.3d).unit(UnitDistance.YARDS).build())
                .resultUnitDistance(UnitDistance.METERS)
                .build();

        CalculationResponse result = service.addDistances(request);


        assertThat(format(result.getResult().getValue())).isEqualTo("5.218");
        assertThat(result.getResult().getUnit()).isEqualTo(UnitDistance.METERS);
    }

    @Test
    void sumDistanceAYardsDistanceBMetersToMeters() {
        CalculationRequest request = CalculationRequest.builder()
                .distanceA(Distance.builder().value(2.2d).unit(UnitDistance.YARDS).build())
                .distanceB(Distance.builder().value(3.3d).unit(UnitDistance.METERS).build())
                .resultUnitDistance(UnitDistance.METERS)
                .build();

        CalculationResponse result = service.addDistances(request);


        assertThat(format(result.getResult().getValue())).isEqualTo("5.312");
        assertThat(result.getResult().getUnit()).isEqualTo(UnitDistance.METERS);
    }

    public static String format(double num) {
        DecimalFormatSymbols decimalSymbols = DecimalFormatSymbols.getInstance();
        decimalSymbols.setDecimalSeparator('.');
        return new DecimalFormat("0.000", decimalSymbols).format(num);
    }
    
}