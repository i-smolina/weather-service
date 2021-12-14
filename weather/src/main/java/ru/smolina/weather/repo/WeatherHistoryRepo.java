package ru.smolina.weather.repo;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import ru.smolina.weather.domain.WeatherHistory;


public interface WeatherHistoryRepo extends JpaRepository<WeatherHistory, Long> {
	
	public WeatherHistory findByWeatherDate(LocalDate localDate);

}
