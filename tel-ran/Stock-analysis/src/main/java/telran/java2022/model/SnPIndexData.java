package telran.java2022.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "date")
@ToString
public class SnPIndexData implements Serializable {
		private static final long serialVersionUID = 170223932460651446L;
//		@Id
		LocalDate date;
		Double closeLast;
		String volume;
		Double open;
		Double high;
		Double low;
}
