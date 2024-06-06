package telran.java52.book.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "name")
@Entity
public class Author implements Serializable {
	private static final long serialVersionUID = -3391550214970936750L;
	@Id
	String name;
	LocalDate birthDate;
}
