package org.echocat.kata.java.part1;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Library {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final MagazineRepository magazineRepository;

  public Library(
      BookRepository bookRepository,
      AuthorRepository authorRepository,
      MagazineRepository magazineRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
    this.magazineRepository = magazineRepository;
  }

  public BookOrMagazineResult findABookOrMagazineByISBN(String isbn) {
    Optional<BookOrMagazineResult> result1 =
        bookRepository.findAll().stream()
            .filter(book -> book.getIsbn().equals(isbn))
            .findFirst()
            .map(this::getBook);

    Optional<BookOrMagazineResult> result2 =
        magazineRepository.findAll().stream()
            .filter(magazine -> magazine.getIsbn().equals(isbn))
            .findFirst()
            .map(this::getMagazine);

    if (result1.isPresent()) return result1.get();
    else if (result2.isPresent()) return result2.get();
    else throw new RuntimeException("no book or magazine found");
  }

  private BookOrMagazineResult getMagazine(Magazine magazine) {
    return BookOrMagazineResult.builder()
        .type("magazine")
        .title(magazine.getTitle())
        .authors(magazine.getAuthors())
        .publishedAt(magazine.getPublishedAt())
        .isbn(magazine.getIsbn())
        .build();
  }

  private BookOrMagazineResult getBook(Book book) {
    return BookOrMagazineResult.builder()
        .type("book")
        .title(book.getTitle())
        .authors(book.getAuthors())
        .description(book.getDescription())
        .isbn(book.getIsbn())
        .build();
  }

  public BooksAndMagazinesResult findAllBooksAndMagazinesByAuthorsEmail(String authorEmail) {
    List<Book> bookList =
        bookRepository.findAll().stream()
            .filter(book -> book.getAuthors().contains(authorEmail))
            .collect(Collectors.toList());
    List<Magazine> magazineList =
        magazineRepository.findAll().stream()
            .filter(magazine -> magazine.getAuthors().contains(authorEmail))
            .collect(Collectors.toList());
    return BooksAndMagazinesResult.builder().books(bookList).magazines(magazineList).build();
  }

  public List<BookOrMagazineResult> getAllBooksAndMagazinesSortedByTitle() {
    Stream<BookOrMagazineResult> stream1 = bookRepository.findAll().stream().map(this::getBook);
    Stream<BookOrMagazineResult> stream2 =
        magazineRepository.findAll().stream().map(this::getMagazine);
    Stream<BookOrMagazineResult> combined = Stream.concat(stream1, stream2);
    return combined
        .sorted(Comparator.comparing(BookOrMagazineResult::getTitle))
        .collect(Collectors.toList());
  }


  public void printAllBooksAndMagazines() {
    Stream<BookOrMagazineResult> stream1 = bookRepository.findAll().stream().map(this::getBook);
    Stream<BookOrMagazineResult> stream2 =
        magazineRepository.findAll().stream().map(this::getMagazine);
    Stream<BookOrMagazineResult> combined = Stream.concat(stream1, stream2);

    combined
        .forEach(bookOrMagazineResult -> {
          System.out.println("==========");
          bookOrMagazineResult.getAuthors().stream()
              .map(email -> authorRepository.findAll()
                  .stream().filter(author -> author.getEmail().equals(email)).findFirst())
                  .filter(Optional::isPresent)
                  .map(Optional::get)
                  .forEach(System.out::println);
          System.out.println(bookOrMagazineResult);
          System.out.println("==========");
        });
  }

}
