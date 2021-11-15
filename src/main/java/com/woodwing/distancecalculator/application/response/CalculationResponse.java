package com.woodwing.distancecalculator.application.response;

import com.woodwing.distancecalculator.domain.Distance;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CalculationResponse {
    Distance result;
}
