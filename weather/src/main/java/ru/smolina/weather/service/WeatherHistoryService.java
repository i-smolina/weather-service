package ru.smolina.weather.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.smolina.weather.domain.WeatherHistory;
import ru.smolina.weather.exception.InvalidTempValueException;
import ru.smolina.weather.repo.WeatherHistoryRepo;

@Service
public class WeatherHistoryService {

	@Autowired
	private WeatherHistoryRepo weatherHistRepo;

	public WeatherHistory getWeatherToday() throws URISyntaxException, IOException, InterruptedException {

		WeatherHistory weatherHist = weatherHistRepo.findByWeatherDate(LocalDate.now());
		if (weatherHist == null) {
			String temp = getTempFromYandex();
			weatherHist = new WeatherHistory();
			weatherHist.setWeatherDate(LocalDate.now());
			weatherHist.setWeatherValue(temp);
			return weatherHistRepo.save(weatherHist);
		} else
			return weatherHist;
	}

	private String getTempFromYandex() throws URISyntaxException, IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				// .uri(new URI("https://api.weather.yandex.ru/v2/forecast?lat=55.75396&lon=37.620393&extra=true")).GET()		// Moscow
				.uri(new URI("https://api.weather.yandex.ru/v2/forecast?lat=59.9342802&lon=30.3350986&extra=true"))				// Spb
				.GET()
				.header("X-Yandex-API-Key", "4ef60b3b-d86f-4f77-9c9d-b9a85a392ba6").build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String temp = extractTempFromJson(response.body());
		
		if (temp == null || temp.isEmpty())
			throw new InvalidTempValueException("Ошибка получения температуры с https://api.weather.yandex.ru");
		return temp;
	}

	private String extractTempFromJson(String json) {

		String text = json.substring(json.indexOf("fact"));
		Pattern pattern = Pattern.compile("\"temp\":(?<value>-?\\d+)");
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			return matcher.group("value");
		}
		return null;
	}
}
