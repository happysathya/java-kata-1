package org.echocat.kata.java.part1;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Book {

  private String title;

  private String isbn;

  private List<String> authors;

  private String description;
}
