package telran.java52.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java52.book.model.Book;
import telran.java52.book.model.Publisher;

public interface PublisherRepository {

//	@Query("select distinct p.publisherName from Book b join b.publisher p join b.authors a where a.name = ?1")
//	List<String> findPublishersByAuthor(String authorName);

	Stream<Publisher> findDistinctByBooksAuthorsName(String authorName);

	Publisher save(Publisher publisher);

	Optional<Publisher> findById(String name);

	void deleteById(String name);

}
