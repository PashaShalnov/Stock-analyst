package telran.java2022.dao;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import telran.java2022.model.SnPIndexData;

public interface SnPIndexRepository extends CrudRepository<SnPIndexData, LocalDate> {

	boolean existsByDate(LocalDate localDate);
}
