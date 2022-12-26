package telran.java2022.csvparserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import telran.java2022.dao.SnPIndexRepository;
import telran.java2022.model.SnPIndexData;

@Service
@RequiredArgsConstructor
public class CsvParserServiceImpl implements CsvParserService {

	final SnPIndexRepository indexRepository;
	
	@Override
	public Set<SnPIndexData> getDataFromCSV() {
		Set<SnPIndexData> records = new HashSet<>();
		Path pathToFile = Paths.get("HistoricalData_1671796493141.csv");
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { 
			@SuppressWarnings("unused")
			String headerLine = br.readLine();
			String line = br.readLine();
			while (line != null) {
				String[] attributes = line.split(",");
				SnPIndexData data = createSnPIndexData(attributes);
				records.add(data);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		addDataToDb(records);
		records.forEach(System.out::println);
		return records;
	}

	private static SnPIndexData createSnPIndexData(String[] metadata) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date = LocalDate.parse(metadata[0], formatter);
		Double closeLast = Double.parseDouble(metadata[1]);
		String volume = metadata[2];
		Double open = Double.parseDouble(metadata[3]);
		Double high = Double.parseDouble(metadata[4]);
		Double low = Double.parseDouble(metadata[5]);
		return new SnPIndexData(date, closeLast, volume, open, high, low);
	}

	@Override
	public void addDataToDb(Set<SnPIndexData> records) {
		Set<SnPIndexData> set = records.stream()
		.filter(el -> existsByDate(el.getDate().toString()) == false)
		.collect(Collectors.toSet());
		indexRepository.saveAll(set);	
		
	}

	@Override
	public boolean existsByDate(String localDate) {
		LocalDate ld = LocalDate.parse(localDate);
		return indexRepository.existsByDate(ld);
	}

}
