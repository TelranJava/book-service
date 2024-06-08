package telran.java52.book.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.java52.book.model.Author;
import telran.java52.book.model.Book;
import telran.java52.book.model.Publisher;

public interface BookRepository extends JpaRepository<Book, String> {

//	@Query("select b from Book b where b.authors.contains(?1)")
	 @Query("select b from Book b join b.authors a where a.name = ?1")
	List<Book> findBooksByAuthor(Author author);

	 @Query("select b from Book b  where b.publisher = ?1")
	List<Book> findBooksByPublisher(Publisher publisher);
}
