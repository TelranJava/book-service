package telran.java52.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import telran.java52.book.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public void deleteByAuthorsName(String name) {
//		String jpql = "DELETE FROM Book b WHERE b.authors.name = :name";
//		em.createQuery(jpql).setParameter("name", name).executeUpdate();
		
		em.createQuery("delete from Book b where ?1 members of b.authors").setParameter(1, name).executeUpdate();
	}

	@Override
	public boolean existsById(String isbn) {
		return em.find(Book.class, isbn) != null;
	}

	@Override
	public Book save(Book book) {
		em.persist(book); // только добавление нового
		return book;
	}

	@Override
	public Optional<Book> findById(String isbn) {
		return Optional.ofNullable(em.find(Book.class, isbn));
	}

	@Override
	public void deleteById(String isbn) {
		em.remove(em.find(Book.class, isbn));
	}

	@Override
	public Stream<Book> findByAuthorsName(String name) {
		return em.createQuery("select b from Book b join b.authors a where a.name = ?1", Book.class)
				.setParameter(1, name).getResultStream();
	}

	@Override
	public Stream<Book> findByPublisherPublisherName(String name) {
		return em.createQuery("select b from Book b join b.publisher a where a.publisherName = ?1", Book.class)
				.setParameter(1, name).getResultStream();
	}
}
