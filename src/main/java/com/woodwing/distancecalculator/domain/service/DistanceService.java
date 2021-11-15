/*
 * Copyright (c) 2021 TomTom N.V. All rights reserved.
 *
 * This software is the proprietary copyright of TomTom N.V. and its subsidiaries and may be
 * used for internal evaluation purposes or commercial use strictly subject to separate
 * licensee agreement between you and TomTom. If you are the licensee, you are only permitted
 * to use this Software in accordance with the terms of your license agreement. If you are
 * not the licensee then you are not authorised to use this software in any manner and should
 * immediately return it to TomTom N.V.
 */

package com.woodwing.distancecalculator.domain.service;

import com.woodwing.distancecalculator.application.request.CalculationRequest;
import com.woodwing.distancecalculator.application.response.CalculationResponse;
import com.woodwing.distancecalculator.domain.Distance;
import com.woodwing.distancecalculator.domain.DistanceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DistanceService {

    public CalculationResponse addDistances(CalculationRequest request) {
        var distanceAConverted = DistanceFactory.toUnit(request.getDistanceA(), request.getResultUnitDistance());
        var distanceBConverted = DistanceFactory.toUnit(request.getDistanceB(), request.getResultUnitDistance());
        return CalculationResponse.builder()
                .result(Distance.builder()
                        .value(distanceAConverted.getValue() + distanceBConverted.getValue())
                        .unit(request.getResultUnitDistance())
                        .build())
                .build();
    }
}
