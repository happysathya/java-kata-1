package org.echocat.kata.java.part1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import org.junit.Test;

public class BookRepositoryTest {

  @Test
  public void shouldReadBooksCSVFileIntoBookRepository() throws Exception {
    BookRepository bookRepository = new BookRepository();

    List<Book> books = bookRepository.findAll();
    assertFalse(books.isEmpty());

    Book book = books.stream().findFirst().get();
    assertNotNull(book.getTitle());
    assertNotNull(book.getIsbn());
    assertFalse(book.getAuthors().isEmpty());
    assertNotNull(book.getDescription());
  }
}
