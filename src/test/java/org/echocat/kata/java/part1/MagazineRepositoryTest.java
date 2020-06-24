package org.echocat.kata.java.part1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import org.junit.Test;

public class MagazineRepositoryTest {

  @Test
  public void shouldReadMagazineCSVFileIntoMagazineRepository() throws Exception {
    MagazineRepository magazineRepository = new MagazineRepository();

    List<Magazine> magazines = magazineRepository.findAll();
    assertFalse(magazines.isEmpty());

    Magazine magazine = magazines.stream().findFirst().get();
    assertNotNull(magazine.getTitle());
    assertNotNull(magazine.getIsbn());
    assertFalse(magazine.getAuthors().isEmpty());
    assertNotNull(magazine.getPublishedAt());
  }
}
