package com.woodwing.distancecalculator.domain;

public enum UnitDistance {
    YARDS{
        @Override
        protected Double conversionFactor(UnitDistance toUnit){
            switch (toUnit){
                case YARDS: return 1.0;
                case METERS: return 0.9144;
                default: throw new UnsupportedOperationException("Unsupported Conversion: " + toUnit);
            }
        }
    },
    METERS{
        @Override
        protected Double conversionFactor(UnitDistance toUnit){
            switch (toUnit){
                case YARDS: return 1.09361;
                case METERS: return 1.0;
                default: throw new UnsupportedOperationException("Unsupported Conversion: " + toUnit);
            }
        }
    };

    public Double convert(Double distance, UnitDistance toUnit) {
        return distance * conversionFactor(toUnit);
    }

    protected abstract Double conversionFactor(UnitDistance toUnit);
}
