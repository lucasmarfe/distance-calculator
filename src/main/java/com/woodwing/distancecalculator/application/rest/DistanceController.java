package com.woodwing.distancecalculator.application.rest;

import com.woodwing.distancecalculator.application.request.CalculationRequest;
import com.woodwing.distancecalculator.application.response.CalculationResponse;
import com.woodwing.distancecalculator.domain.Distance;
import com.woodwing.distancecalculator.domain.UnitDistance;
import com.woodwing.distancecalculator.domain.service.DistanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api
@RestController
@Slf4j
public class DistanceController {

    private final DistanceService service;

    @Autowired
    public DistanceController(DistanceService service) {
        this.service = service;
    }

    @GetMapping(
            value = "/distance",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<CalculationResponse> calculateDistance(
            @ApiParam(required = true) @RequestParam Double distanceA,
            @ApiParam(required = true) @RequestParam Double distanceB,
            @ApiParam(required = true) @RequestParam UnitDistance distanceAUnitDistance,
            @ApiParam(required = true) @RequestParam UnitDistance distanceBUnitDistance,
            @ApiParam(required = true) @RequestParam UnitDistance resultUnitDistance) {
        log.info(
                String.format(
                        "/distance?distanceA=%f&distanceB=%f&distanceAUnit=%s&distanceBUnit=%s&resultUnit=%s",
                        distanceA, distanceB, distanceAUnitDistance, distanceBUnitDistance, resultUnitDistance));
        var request =
                CalculationRequest.builder()
                        .distanceA(Distance.builder().value(distanceA).unit(distanceAUnitDistance).build())
                        .distanceB(Distance.builder().value(distanceB).unit(distanceBUnitDistance).build())
                        .resultUnitDistance(resultUnitDistance)
                        .build();
        return ResponseEntity.ok(service.addDistances(request));
    }
}
