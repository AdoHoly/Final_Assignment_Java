package sk.ness.academy.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sk.ness.academy.dao.ArticleDAO;
import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

  @Resource
  private ArticleDAO articleDAO;

  @Override
  public Article findByID(final Integer articleId) {
	  return this.articleDAO.findByID(articleId);
  }

  @Override
  public Comment findCommentByID(final Integer commentId) { return this.articleDAO.findCommentByID(commentId);}

  @Override
  public void deleteByID(final Integer articleId) {this.articleDAO.deleteByID(articleId);}

  @Override
  public void deleteCommentByID(final Integer commentId) {this.articleDAO.deleteCommentByID(commentId);}

  @Override
  public List<Article> findAll() {
    return this.articleDAO.findAll();
  }

  @Override
  public List<Comment> findAllComments() {
    return this.articleDAO.findAllComments();
  }

  @Override
  public void createArticle(final Article article) {
    this.articleDAO.persist(article);
  }

  @Override
  public void createComment(final Comment comment) {
    this.articleDAO.persist(comment);
  }

  @Override
  public void ingestArticles(final String jsonArticles) {
    throw new UnsupportedOperationException("Article ingesting not implemented.");
  }

}
