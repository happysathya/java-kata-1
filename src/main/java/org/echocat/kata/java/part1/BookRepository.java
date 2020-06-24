package org.echocat.kata.java.part1;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.SneakyThrows;

public class BookRepository {

  private final List<Book> books;

  public BookRepository() {
    books = loadDataFromCSV();
  }

  private static Optional<Book> readLine(String line) {
    String[] split = line.split(";");
    if (split.length == 4) {
      return Optional.of(
          Book.builder()
              .title(split[0].trim())
              .isbn(split[1].trim())
              .authors(Arrays.asList(split[2].split(",")))
              .description(split[3].trim())
              .build());
    } else {
      return Optional.empty();
    }
  }

  @SneakyThrows
  private List<Book> loadDataFromCSV() {
    BufferedReader reader =
        Files.newBufferedReader(
            Paths.get(
                ClassLoader.getSystemResource("org/echocat/kata/java/part1/data/books.csv")
                    .toURI()));
    return reader
        .lines()
        .skip(1)
        .map(BookRepository::readLine)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());
  }

  public List<Book> findAll() {
    return Collections.unmodifiableList(books);
  }
}
