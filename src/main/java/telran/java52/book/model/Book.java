package telran.java52.book.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "isbn")
@Entity
@Table(name = "BOOK") // как называется таблица которой соответствует эта сущность в уже существующей таблице
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1950334873767354808L;
	
	@Id
	@Column(name = "ISBN") // имя поля как в базе данных соответственно если цепляемся к существующей таблице и данным
	String isbn;
	@Column(name = "TITLE")
	String title;
	@ManyToMany
	@JoinTable( // описание таблицы связи
			name = "BOOK_AUTHORS", // имя существующей таблицы к которой мы привязываемся
			joinColumns = @JoinColumn(name = "BOOK_ISBN"), // поле которое отвечает за ключ книги(текущая сущность)
			inverseJoinColumns = @JoinColumn(name = "AUTHORS_NAME") // поле которое отвечает за ключ автора(родительская сущность)
			)
	Set<Author> authors;
	@ManyToOne  			 // описать дома 
	Publisher publisher;
}
