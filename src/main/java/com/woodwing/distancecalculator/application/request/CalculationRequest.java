package com.woodwing.distancecalculator.application.request;

import com.woodwing.distancecalculator.domain.Distance;
import com.woodwing.distancecalculator.domain.UnitDistance;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CalculationRequest {

  Distance distanceA;

  Distance distanceB;

  UnitDistance resultUnitDistance;
}
