package com.woodwing.distancecalculator.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Builder
@Getter
@ToString
@Value
public class Distance {

    Double value;
    UnitDistance unit;

}
