package sk.ness.academy.dao;

import java.util.List;

import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;
import sk.ness.academy.dto.NoCommentArticle;

public interface ArticleDAO {

	  /** Returns {@link Article} with provided ID */
	  Article findByID(Integer articleId);

	  /** Returns {@link Comment} with provided ID */
	  Comment findCommentByID(Integer commentId);

	  /** Returns all available {@link Article}s */
	  List<Article> findAll();

	  /** Returns all available {@link Comment}s */
	  List<Comment> findAllComments();

	List<NoCommentArticle> findAllArticles();


	  List<NoCommentArticle> findAllArticlesWithText(String searchText);


	  /** Persists {@link Article} into the DB */
	  void persist(Article article);

	  /** Persists {@link Article} into the DB */
	  void persist(Comment comment);

	  /** Deletes {@link Article} with provided ID */
	  void deleteByID(Integer articleId);

	  /** Deletes {@link Comment} with provided ID */
	  void deleteCommentByID(Integer commentId);



	}
