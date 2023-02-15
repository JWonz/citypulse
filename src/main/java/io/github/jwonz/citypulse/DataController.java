package io.github.jwonz.citypulse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;
import java.nio.file.Paths;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class DataController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/getGeoJson")
	public ResponseEntity<byte[]> getGeoJson(@RequestParam(value = "partition", defaultValue = "0") int partition) {
//		File file = new File("C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\TAXPARCEL_CONDOUNITSTACK_LGIM.json");
//		return new String(Files.readAllBytes(file.toPath()));
		if (partition < 0 || partition > 8) {
			return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}

		String[] partFiles = {
				"C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\subsets\\partitions\\TAXPARCEL_CONDOUNITSTACK_LGIM_4326_1-50k.geojson",
				"C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\subsets\\partitions\\TAXPARCEL_CONDOUNITSTACK_LGIM_4326_50k-100k.geojson",
				"C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\subsets\\partitions\\TAXPARCEL_CONDOUNITSTACK_LGIM_4326_100k-150k.geojson",
				"C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\subsets\\partitions\\TAXPARCEL_CONDOUNITSTACK_LGIM_4326_150k-200k.geojson",
				"C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\subsets\\partitions\\TAXPARCEL_CONDOUNITSTACK_LGIM_4326_200k-250k.geojson",
				"C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\subsets\\partitions\\TAXPARCEL_CONDOUNITSTACK_LGIM_4326_250k-300k.geojson",
				"C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\subsets\\partitions\\TAXPARCEL_CONDOUNITSTACK_LGIM_4326_300k-350k.geojson",
				"C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\subsets\\partitions\\TAXPARCEL_CONDOUNITSTACK_LGIM_4326_350k-400k.geojson",
				"C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\subsets\\partitions\\TAXPARCEL_CONDOUNITSTACK_LGIM_4326_400k-444k.geojson"
		};

		try {
			File file = new File(partFiles[partition]);
//			File file = new File("C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\TAXPARCEL_CONDOUNITSTACK_LGIM_4326.json");
			byte[] fileBytes = Files.readAllBytes(Paths.get(file.getPath()));

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setContentLength(fileBytes.length);
			headers.add("Access-Control-Allow-Origin", "*");

			return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
		} catch (IOException ex) {
			System.err.println("An error occurred while reading the JSON file: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}