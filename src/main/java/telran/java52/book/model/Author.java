package telran.java52.book.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	@ManyToMany(mappedBy = "authors", cascade = CascadeType.REMOVE) // mappedBy ставится на стороне родительской сущности, 
						//	cascade = REMOVE значит что hybernate сам удалит все связанные книги, на базу это не влияет
	Set<Book>books;

	public Author(String name, LocalDate birthDate) {
		this.name = name;
		this.birthDate = birthDate;
	}
	
}
