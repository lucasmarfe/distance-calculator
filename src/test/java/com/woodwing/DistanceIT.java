package com.woodwing;

import com.woodwing.distancecalculator.DistanceCalculatorApplication;
import com.woodwing.distancecalculator.domain.UnitDistance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DistanceCalculatorApplication.class)
@AutoConfigureMockMvc
class DistanceIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Sum Distances: When making a valid request, should return the sum of the 2 provided distances.")
    void shouldReturnSumSuccessfully() throws Exception {
        mockMvc
                .perform(
                        get("/distance")
                                .contentType(MediaType.APPLICATION_JSON)
                                .queryParam("distanceA", String.valueOf(1d))
                                .queryParam("distanceB", String.valueOf(1d))
                                .queryParam("distanceAUnitDistance", String.valueOf(UnitDistance.YARDS))
                                .queryParam("distanceBUnitDistance", String.valueOf(UnitDistance.YARDS))
                                .queryParam("resultUnitDistance", String.valueOf(UnitDistance.YARDS)))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.result.value")
                                .value(2d));
    }

    @Test
    @DisplayName("Sum Distances: When making an invalid request (distance value), should return formatted error.")
    void shouldReturnFormattedErrorDoubleConversionException() throws Exception {
        mockMvc
                .perform(
                        get("/distance")
                                .contentType(MediaType.APPLICATION_JSON)
                                .queryParam("distanceA", "dummy-error")
                                .queryParam("distanceB", String.valueOf(1d))
                                .queryParam("distanceAUnitDistance", String.valueOf(UnitDistance.YARDS))
                                .queryParam("distanceBUnitDistance", String.valueOf(UnitDistance.YARDS))
                                .queryParam("resultUnitDistance", String.valueOf(UnitDistance.YARDS)))
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.error.message")
                                .value("Failed to convert value of type 'java.lang.String' to required type 'java.lang.Double'; nested exception is java.lang.NumberFormatException: For input string: \"dummy-error\""));
    }

    @Test
    @DisplayName("Sum Distances: When making an invalid request (enum value), should return formatted error.")
    void shouldReturnFormattedErrorEnumConversionException() throws Exception {
        mockMvc
                .perform(
                        get("/distance")
                                .queryParam("distanceA", String.valueOf(1d))
                                .queryParam("distanceB", String.valueOf(1d))
                                .queryParam("distanceAUnitDistance", "dummy-unit")
                                .queryParam("distanceBUnitDistance", String.valueOf(UnitDistance.YARDS))
                                .queryParam("resultUnitDistance", String.valueOf(UnitDistance.YARDS)))
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.error.message",
                                containsString("Failed to convert value of type 'java.lang.String' to required type 'com.woodwing.distancecalculator.domain.UnitDistance';")));
    }
}
