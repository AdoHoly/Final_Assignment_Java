package sk.ness.academy.dao;

import java.util.List;

import sk.ness.academy.dto.Author;
import sk.ness.academy.dto.AuthorStats;
import sk.ness.academy.dto.NoCommentArticle;

public interface AuthorDAO {

  /** Returns all available {@link Author}s */
  List<Author> findAll();

  List<AuthorStats> findAuthorsWithCount();

}

