package org.echocat.kata.java.part1;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Author {

  private String email;

  private String firstname;

  private String lastname;
}
