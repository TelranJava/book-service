package telran.java52.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import telran.java52.book.model.Book;

public interface BookRepository {

	Stream<Book> findByPublisherPublisherName(String name);

	Stream<Book> findByAuthorsName(String name);

	void deleteByAuthorsName(String name);

	boolean existsById(String isbn);

	Book save(Book book);

	Optional<Book> findById(String isbn);

	void deleteById(String isbn);
}
