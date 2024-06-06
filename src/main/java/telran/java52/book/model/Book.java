package telran.java52.book.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(of = "isbn")
@Entity
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1950334873767354808L;
	
	@Id
	String isbn;
	@Setter
	String title;
	@Singular
	@ManyToMany
	Set<Author> authors;
	@Setter
	@ManyToOne
	Publisher publisher;
}
