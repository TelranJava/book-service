package telran.java52.book.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "publisherName")
@Entity
public class Publisher implements Serializable{
	private static final long serialVersionUID = -7084036100574287892L;
	
	@Id
	String publisherName;
	
	@Override
	public String toString() {
		return publisherName;
	}
}
