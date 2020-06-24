package org.echocat.kata.java.part1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

public class LibraryTest {

  BookRepository bookRepository = new BookRepository();
  MagazineRepository magazineRepository = new MagazineRepository();
  AuthorRepository authorRepository = new AuthorRepository();

  Library library = new Library(bookRepository, authorRepository, magazineRepository);

  @Test
  public void shouldFindABookByISBN() {
    String bookISBN = "2145-8548-3325";
    BookOrMagazineResult searchOutput = library.findABookOrMagazineByISBN(bookISBN);

    assertEquals("book", searchOutput.getType());
    assertEquals("Das große GU-Kochbuch Kochen für Kinder", searchOutput.getTitle());
  }

  @Test
  public void shouldFindAMagazineByISBN() {
    String magazineISBN = "1313-4545-8875";
    BookOrMagazineResult searchOutput = library.findABookOrMagazineByISBN(magazineISBN);

    assertEquals("magazine", searchOutput.getType());
    assertEquals("Vinum", searchOutput.getTitle());
  }

  @Test
  public void shouldFindAllBooksAndMagazinesByAuthorEmail() {
    String authorEmail = "null-ferdinand@echocat.org";
    BooksAndMagazinesResult allBooksAndMagazinesByAuthorsEmail =
        library.findAllBooksAndMagazinesByAuthorsEmail(authorEmail);

    assertEquals(1, allBooksAndMagazinesByAuthorsEmail.getBooks().size());
    Book book = allBooksAndMagazinesByAuthorsEmail.getBooks().stream().findFirst().get();
    assertEquals("Das große GU-Kochbuch Kochen für Kinder", book.getTitle());

    assertEquals(1, allBooksAndMagazinesByAuthorsEmail.getMagazines().size());
    Magazine magazine =
        allBooksAndMagazinesByAuthorsEmail.getMagazines().stream().findFirst().get();
    assertEquals("Gourmet", magazine.getTitle());
  }

  @Test
  public void shouldSortAllBooksAndMagazinesByTitle() {
    List<BookOrMagazineResult> allBooksAndMagazinesSortedByTitle =
        library.getAllBooksAndMagazinesSortedByTitle();

    List<BookOrMagazineResult> booksSorted =
        allBooksAndMagazinesSortedByTitle.stream()
            .filter(bookOrMagazineResult -> bookOrMagazineResult.getType().equals("book"))
            .collect(Collectors.toList());

    List<BookOrMagazineResult> magazinesSorted =
        allBooksAndMagazinesSortedByTitle.stream()
            .filter(bookOrMagazineResult -> bookOrMagazineResult.getType().equals("magazine"))
            .collect(Collectors.toList());

    assertFalse(booksSorted.isEmpty());
    assertFalse(magazinesSorted.isEmpty());

    BookOrMagazineResult firstItem = allBooksAndMagazinesSortedByTitle.get(0);
    assertEquals("Beautiful cooking", firstItem.getTitle());
    assertEquals("magazine", firstItem.getType());

    BookOrMagazineResult secondItem = allBooksAndMagazinesSortedByTitle.get(1);
    assertEquals("Cooking for gourmets", secondItem.getTitle());
    assertEquals("magazine", secondItem.getType());
  }

  @Test
  public void testPrintingAllItems() {
    library.printAllBooksAndMagazines();
  }
}
