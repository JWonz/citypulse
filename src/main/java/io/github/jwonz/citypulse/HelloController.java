package io.github.jwonz.citypulse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;
import java.nio.file.Paths;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/getGeoJson")
	public ResponseEntity<byte[]> getGeoJson() {
//		File file = new File("C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\TAXPARCEL_CONDOUNITSTACK_LGIM.json");
//		return new String(Files.readAllBytes(file.toPath()));

		try {
			File file = new File("C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\TAXPARCEL_CONDOUNITSTACK_LGIM.json");
			byte[] fileBytes = Files.readAllBytes(Paths.get(file.getPath()));

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setContentLength(fileBytes.length);

			return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
		} catch (IOException ex) {
			System.err.println("An error occurred while reading the JSON file: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}