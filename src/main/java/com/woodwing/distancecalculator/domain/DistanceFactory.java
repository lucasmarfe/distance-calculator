package com.woodwing.distancecalculator.domain;

public class DistanceFactory {

    private DistanceFactory() {
    }

    public static Distance toUnit(Distance distance, UnitDistance toUnit) {
        Double convertedValue = distance.getUnit().convert(distance.getValue(), toUnit);
        return Distance.builder()
                .value(convertedValue)
                .unit(toUnit)
                .build();
    }
}
