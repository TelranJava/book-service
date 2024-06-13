package telran.java52.book.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java52.book.dao.AuthorRepository;
import telran.java52.book.dao.BookRepository;
import telran.java52.book.dao.PublisherRepository;
import telran.java52.book.dto.AuthorDto;
import telran.java52.book.dto.BookDto;
import telran.java52.book.dto.exception.EntityNotFoundException;
import telran.java52.book.model.Author;
import telran.java52.book.model.Book;
import telran.java52.book.model.Publisher;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	final BookRepository bookRepository;
	final AuthorRepository authorRepository;
	final PublisherRepository publisherRepository;
	final ModelMapper modelMapper;

	@Transactional
	@Override
	public Boolean addBook(BookDto bookDto) {
		if (bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		// Publisher
		Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
				.orElse(publisherRepository.save(new Publisher(bookDto.getPublisher())));
		// Authors
		Set<Author> authors = bookDto.getAuthors().stream()
				.map(a -> authorRepository.findById(a.getName())
						.orElse(authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
				.collect(Collectors.toSet());

		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
		bookRepository.save(book);
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public BookDto findBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional
	@Override
	public BookDto updateBookTitle(String isbn, String newTitle) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		book.setTitle(newTitle);
		bookRepository.save(book);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional
	@Override
	public BookDto removeBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		bookRepository.deleteById(isbn);
		return modelMapper.map(book, BookDto.class);
	}

	// TODO now it's not working
	@Transactional(readOnly = true)
	@Override
	public BookDto[] findBooksByAuthor(String name) {
		Author author = authorRepository.findById(name).orElseThrow(EntityNotFoundException::new);
		return bookRepository.findBooksByAuthor(author)
				.stream().map(b -> modelMapper.map(b, BookDto.class))
				.toArray(BookDto[]::new);
	}

	@Transactional(readOnly = true)
	@Override
	public BookDto[] findBooksByPublisher(String publisher) {
		return bookRepository.findBooksByPublisher(new Publisher(publisher)).stream()
				.map(b -> modelMapper.map(b, BookDto.class)).toArray(BookDto[]::new);
	}

	@Transactional(readOnly = true)
	@Override
	public AuthorDto[] findBookAuthors(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		return book.getAuthors().stream()
				.map(a -> modelMapper.map(a, AuthorDto.class))
				.toArray(AuthorDto[]::new);
	}

	// TODO now it's not working
	@Transactional(readOnly = true)
	@Override
	public String[] findPublishersByAuthor(String name) {
		Author author = authorRepository.findById(name).orElseThrow(EntityNotFoundException::new);
		List<Book> books =  bookRepository.findBooksByAuthor(author);
		return books.stream()
				.map(b -> b.getPublisher().toString())
				.toArray(String[] ::new);
	}

	// TODO now it's not working
	@Transactional
	@Override
	public AuthorDto removeAuthor(String authorName) {
		Author author = authorRepository.findById(authorName).orElseThrow(EntityNotFoundException::new);
		List<Book> books = bookRepository.findBooksByAuthor(author);
		if (books.size() > 0) {
			for (int i = 0; i < books.size(); i++) {
				Book book = books.get(i);
				Set<Author> authors = book.getAuthors();
				if (authors.contains(author) && authors.size() > 1) {
					authors.remove(author);
					book.setAuthors(authors);
					bookRepository.save(book);
				}
			}
		}
		books = bookRepository.findBooksByAuthor(author);
		if (books.size() > 0) {
			throw new RuntimeException("The author cannot be removed");
		}
		authorRepository.deleteById(author.getName());
		return modelMapper.map(author, AuthorDto.class);
	}

}
