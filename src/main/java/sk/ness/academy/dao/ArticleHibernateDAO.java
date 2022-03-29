package sk.ness.academy.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;

@Repository
public class ArticleHibernateDAO implements ArticleDAO {

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  @Override
  public Article findByID(final Integer articleId) {
    Article article = this.sessionFactory.getCurrentSession().get(Article.class, articleId);
    Hibernate.initialize(article.getComments());
    return article;
  }

  @Override
  public Comment findCommentByID(final Integer commentId) {
    return (Comment) this.sessionFactory.getCurrentSession().get(Comment.class, commentId);
  }

  @Override
  public void deleteByID(final Integer articleId){
     this.sessionFactory.getCurrentSession().delete(findByID(articleId));
  }

  @Override
  public void deleteCommentByID(final Integer commentId){
     this.sessionFactory.getCurrentSession().delete(findCommentByID(commentId));
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Article> findAll() {
    List<Article> test = this.sessionFactory.getCurrentSession().createSQLQuery("select * from articles").addEntity(Article.class).list();
    test.stream().map(a -> a.getComments());
    return test;
  }

  @Override
  public List<Comment> findAllComments() {
    return this.sessionFactory.getCurrentSession().createSQLQuery("select * from comments").addEntity(Comment.class).list();
  }

  @Override
  public void persist(final Article article) {
    this.sessionFactory.getCurrentSession().saveOrUpdate(article);
  }

  @Override
  public void persist(final Comment comment) {
    this.sessionFactory.getCurrentSession().saveOrUpdate(comment);
  }

}
