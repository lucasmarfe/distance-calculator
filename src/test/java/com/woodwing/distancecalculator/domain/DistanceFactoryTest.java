package com.woodwing.distancecalculator.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DistanceFactoryTest {

    @Test
    void convertDistanceFromMetersToYards() {
        var metersDistance = Distance.builder().value(1d).unit(UnitDistance.METERS).build();

        Distance response = DistanceFactory.toUnit(metersDistance, UnitDistance.YARDS);

        assertThat(response.getValue()).isEqualTo(1.09361d);
    }

    @Test
    void convertDistanceFromYardsToMeters() {
        var metersDistance = Distance.builder().value(1d).unit(UnitDistance.YARDS).build();

        Distance response = DistanceFactory.toUnit(metersDistance, UnitDistance.METERS);

        assertThat(response.getValue()).isEqualTo(0.9144d);
    }

    @Test
    void convertDistanceFromMetersToMeters() {
        var metersDistance = Distance.builder().value(1d).unit(UnitDistance.METERS).build();

        Distance response = DistanceFactory.toUnit(metersDistance, UnitDistance.METERS);

        assertThat(response.getValue()).isEqualTo(1d);
    }

    @Test
    void convertDistanceFromYardsToYards() {
        var metersDistance = Distance.builder().value(1d).unit(UnitDistance.YARDS).build();

        Distance response = DistanceFactory.toUnit(metersDistance, UnitDistance.YARDS);

        assertThat(response.getValue()).isEqualTo(1d);
    }

}