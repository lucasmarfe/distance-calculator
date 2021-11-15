package com.woodwing.distancecalculator.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Distance {

    private Double value;
    private UnitDistance unit;

}
