package org.echocat.kata.java.part1;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.SneakyThrows;

public class MagazineRepository {

  private final List<Magazine> magazines;

  public MagazineRepository() {
    magazines = loadDataFromCSV();
  }

  private static Optional<Magazine> readLine(String line) {
    String[] split = line.split(";");
    if (split.length == 4) {
      return Optional.of(
          Magazine.builder()
              .title(split[0].trim())
              .isbn(split[1].trim())
              .authors(Arrays.asList(split[2].trim().split(",")))
              .publishedAt(readPublishedAt(split[3]))
              .build());
    } else {
      return Optional.empty();
    }
  }

  private static LocalDate readPublishedAt(String publishedAt) {
    return LocalDate.parse(publishedAt, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
  }

  @SneakyThrows
  private List<Magazine> loadDataFromCSV() {
    BufferedReader reader =
        Files.newBufferedReader(
            Paths.get(
                ClassLoader.getSystemResource("org/echocat/kata/java/part1/data/magazines.csv")
                    .toURI()));
    return reader
        .lines()
        .skip(1)
        .map(MagazineRepository::readLine)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());
  }

  public List<Magazine> findAll() {
    return Collections.unmodifiableList(magazines);
  }
}
