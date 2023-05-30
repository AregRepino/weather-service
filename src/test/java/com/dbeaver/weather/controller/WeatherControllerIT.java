package com.dbeaver.weather.controller;

import com.dbeaver.weather.client.dto.Clouds;
import com.dbeaver.weather.client.dto.OpenWeatherMapResult;
import com.dbeaver.weather.client.dto.Weather;
import com.dbeaver.weather.dto.WeatherHistoryDto;
import com.dbeaver.weather.integration.AbstractIT;
import com.dbeaver.weather.repository.WeatherRepository;
import com.dbeaver.weather.repository.entity.WeatherHistory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.core.Options;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@AutoConfigureWireMock(port = Options.DYNAMIC_PORT)
public class WeatherControllerIT extends AbstractIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().findAndRegisterModules();

    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    public void getCurrentWeather() throws JsonProcessingException {


        OpenWeatherMapResult openWeatherMapResult = new OpenWeatherMapResult();
        openWeatherMapResult.setWeather(Arrays.asList(new Weather()));
        openWeatherMapResult.setClouds(new Clouds());

        String response = OBJECT_MAPPER.writeValueAsString(openWeatherMapResult);

        WeatherHistory weatherExpected = weatherRepository.save(new WeatherHistory(1000, LocalDate.now()));

        givenThat(get(urlPathEqualTo("/weather"))
                .withHeader("Accept", equalTo(MediaType.APPLICATION_JSON_VALUE))
//                .withQueryParam("units", equalTo("metric"))
//                .withQueryParam("lat", equalTo("60.016033"))
//                .withQueryParam("lon", equalTo("30.475637"))
//                .withQueryParam("appid", anyUrl().getPattern())
//                .withRequestBody(equalToJson(JacksonUtil.toString(sentinelImageRequestDto)))
                .willReturn(okJson("{\n" +
                        "  \"coord\": {\n" +
                        "    \"lon\": 30.292,\n" +
                        "    \"lat\": 59.9607\n" +
                        "  },\n" +
                        "  \"weather\": [\n" +
                        "    {\n" +
                        "      \"id\": 804,\n" +
                        "      \"main\": \"Clouds\",\n" +
                        "      \"description\": \"overcast clouds\",\n" +
                        "      \"icon\": \"04n\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 804,\n" +
                        "      \"main\": \"Clouds\",\n" +
                        "      \"description\": \"overcast clouds\",\n" +
                        "      \"icon\": \"04n\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"base\": \"stations\",\n" +
                        "  \"main\": {\n" +
                        "    \"temp\": 261.78,\n" +
                        "    \"feels_like\": 254.78,\n" +
                        "    \"temp_min\": 260.38,\n" +
                        "    \"temp_max\": 262.62,\n" +
                        "    \"pressure\": 1010,\n" +
                        "    \"humidity\": 90\n" +
                        "  },\n" +
                        "  \"visibility\": 10000,\n" +
                        "  \"wind\": {\n" +
                        "    \"speed\": 5,\n" +
                        "    \"deg\": 110\n" +
                        "  },\n" +
                        "  \"clouds\": {\n" +
                        "    \"all\": 100\n" +
                        "  },\n" +
                        "  \"dt\": 1641655349,\n" +
                        "  \"sys\": {\n" +
                        "    \"type\": 2,\n" +
                        "    \"id\": 197864,\n" +
                        "    \"country\": \"RU\",\n" +
                        "    \"sunrise\": 1641624930,\n" +
                        "    \"sunset\": 1641647697\n" +
                        "  },\n" +
                        "  \"timezone\": 10800,\n" +
                        "  \"id\": 581307,\n" +
                        "  \"name\": \"Aptekarskiy\",\n" +
                        "  \"cod\": 200\n" +
                        "}")

                ));

        HttpEntity<String> httpEntity = new HttpEntity<>("");
        ResponseEntity<WeatherHistoryDto> responseEntity = testRestTemplate.exchange("/weather", HttpMethod.GET, httpEntity, WeatherHistoryDto.class);

        Assert.assertNotNull(responseEntity);
        WeatherHistoryDto weatherHistoryDto = responseEntity.getBody();

        WeatherHistory weatherHistory = weatherRepository.findById(weatherHistoryDto.getId()).get();

        Assert.assertNotNull(weatherHistory);

        ResponseEntity<WeatherHistoryDto> responseEntity2 = testRestTemplate.exchange("/weather", HttpMethod.GET, httpEntity, WeatherHistoryDto.class);


        long count = weatherRepository.count();

        assert count == 1;


    }
}
