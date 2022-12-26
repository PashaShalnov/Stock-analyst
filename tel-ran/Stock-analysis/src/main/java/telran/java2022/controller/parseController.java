package telran.java2022.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.csvparserService.CsvParserService;
import telran.java2022.model.SnPIndexData;

@RestController
@RequiredArgsConstructor
public class parseController {

	final CsvParserService csvParserService;
	
	@GetMapping("/curr/snp")
	public Set<SnPIndexData> getDataFromCSV() {
		return csvParserService.getDataFromCSV();
	}
	
	@GetMapping("/curr/finbydate/{localDate}")
	public boolean existsByDate(@PathVariable String localDate) {
		return csvParserService.existsByDate(localDate);
	}	
}
