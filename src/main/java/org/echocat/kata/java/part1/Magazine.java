package org.echocat.kata.java.part1;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Magazine {

  private String title;

  private String isbn;

  private List<String> authors;

  private LocalDate publishedAt;
}
