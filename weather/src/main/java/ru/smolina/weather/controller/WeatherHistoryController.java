package ru.smolina.weather.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.smolina.weather.domain.WeatherHistory;
import ru.smolina.weather.service.WeatherHistoryService;

@RestController
@RequestMapping("weather")
public class WeatherHistoryController {

	@Autowired
	WeatherHistoryService weatherService;

	@GetMapping
	public WeatherHistory weather() throws URISyntaxException, IOException, InterruptedException {
		 return weatherService.getWeatherToday();
	}
}
