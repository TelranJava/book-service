package telran.java52.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import telran.java52.book.model.Publisher;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public Stream<Publisher> findDistinctByBooksAuthorsName(String name) {
		String jpql = "SELECT DISTINCT p FROM Book b JOIN b.publisher p JOIN b.authors a WHERE a.name = :name";
		return em.createQuery(jpql, Publisher.class).setParameter("name", name).getResultStream();
	}

//	@Transactional
	@Override
	public Publisher save(Publisher publisher) {
		em.persist(publisher);// только добавление нового
//		em.merge(publisher); //перезапись существующего или добавление нового
		return publisher;
	}

	@Override
	public Optional<Publisher> findById(String name) {
		return Optional.ofNullable(em.find(Publisher.class, name));
	}

	@Override
	public void deleteById(String name) {
		em.remove(em.find(Publisher.class, name));
	}

}
