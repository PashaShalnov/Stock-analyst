package telran.java2022.csvparserService;

import java.util.Set;

import telran.java2022.model.SnPIndexData;

public interface CsvParserService {
	Set<SnPIndexData> getDataFromCSV();
	
	void addDataToDb(Set<SnPIndexData> set);

	boolean existsByDate(String localDate);
	
	
}
