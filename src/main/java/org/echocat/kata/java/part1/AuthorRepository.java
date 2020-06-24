package org.echocat.kata.java.part1;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.SneakyThrows;

public class AuthorRepository {

  private final List<Author> authors;

  public AuthorRepository() {
    this.authors = loadDataFromCSV();
  }

  private static Optional<Author> readLine(String line) {
    String[] split = line.split(";");
    if (split.length == 3) {
      return Optional.of(
          Author.builder()
              .email(split[0].trim())
              .firstname(split[1].trim())
              .lastname(split[2].trim())
              .build());
    } else {
      return Optional.empty();
    }
  }

  @SneakyThrows
  private List<Author> loadDataFromCSV() {
    BufferedReader reader =
        Files.newBufferedReader(
            Paths.get(
                ClassLoader.getSystemResource("org/echocat/kata/java/part1/data/authors.csv")
                    .toURI()));
    return reader
        .lines()
        .skip(1)
        .map(AuthorRepository::readLine)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());
  }

  public List<Author> findAll() {
    return Collections.unmodifiableList(authors);
  }
}
