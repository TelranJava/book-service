package telran.java52.book.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
	String isbn;
	@Setter
	String title;
	@Singular
	Set<AuthorDto> authors;
	@Setter
	String publisher;
}
