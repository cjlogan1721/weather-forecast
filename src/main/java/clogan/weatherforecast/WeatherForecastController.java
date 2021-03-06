package clogan.weatherforecast;

import org.apache.log4j.Logger;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import clogan.weatherforecast.beans.ForecastService;
import clogan.weatherforecast.beans.RegionService;
import clogan.weatherforecast.domain.Forecast;
import clogan.weatherforecast.domain.Region;
import clogan.weatherforecast.domain.StationList;

@RestController
public class WeatherForecastController {
	
	private Logger logger = Logger.getLogger(WeatherForecastController.class);
	private RegionService regionService = null;
	private ForecastService forecastService = null;
	
	@RequestMapping("/regions")
	public ArrayList<Region> getRegions() {
		logger.debug("Received request for regions");
		StationList stationList = regionService.getRegionList();
		return stationList.getStations();
	}
	
	@RequestMapping("/forecast")
	public Forecast getCityForecast(@RequestParam(value="city", required=true) String city) {
		logger.debug("Received request for forecast:" + city);
		return this.forecastService.getForecast(city);
	}
	
	@Autowired
	public void setForecastService(ForecastService forecastService) {
		logger.debug("ForecastService is set");
		this.forecastService = forecastService;
	}
	
	@Autowired
	public void setRegionService(RegionService regionService) {
		logger.debug("RegionService is set");
		this.regionService = regionService;
	}

}
