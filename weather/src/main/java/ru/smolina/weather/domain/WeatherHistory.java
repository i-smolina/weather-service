package ru.smolina.weather.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WeatherHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private LocalDate weatherDate;
	private String weatherValue;	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	

	public LocalDate getWeatherDate() {
		return weatherDate;
	}
	public void setWeatherDate(LocalDate weatherDate) {
		this.weatherDate = weatherDate;
	}
	public String getWeatherValue() {
		return weatherValue;
	}
	public void setWeatherValue(String weatherValue) {
		this.weatherValue = weatherValue;
	}
	
	
}
