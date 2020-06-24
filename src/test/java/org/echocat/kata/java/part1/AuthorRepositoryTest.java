package org.echocat.kata.java.part1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import org.junit.Test;

public class AuthorRepositoryTest {

  @Test
  public void shouldReadAuthorsCSVFileIntoAuthorRepository() throws Exception {
    AuthorRepository authorRepository = new AuthorRepository();

    List<Author> authors = authorRepository.findAll();
    assertFalse(authors.isEmpty());

    Author author = authors.stream().findFirst().get();
    assertNotNull(author.getEmail());
    assertNotNull(author.getFirstname());
    assertNotNull(author.getLastname());
  }
}
