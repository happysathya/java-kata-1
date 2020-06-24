package org.echocat.kata.java.part1;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class BooksAndMagazinesResult {

  private List<Book> books;
  private List<Magazine> magazines;
}
