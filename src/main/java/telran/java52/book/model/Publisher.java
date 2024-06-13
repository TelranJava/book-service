package telran.java52.book.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "publisherName")
@Entity
public class Publisher implements Serializable {
	private static final long serialVersionUID = -7084036100574287892L;

	@Id
	String publisherName;
	@OneToMany(mappedBy = "publisher") //  mappedBy ставится на стороне родительской сущности 
	Set<Book> books;

	@Override
	public String toString() {
		return publisherName;
	}

	public Publisher(String publisherName) {
		this.publisherName = publisherName;
	}
}
