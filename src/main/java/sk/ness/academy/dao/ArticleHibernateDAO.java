package sk.ness.academy.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;
import sk.ness.academy.dto.NoCommentArticle;

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

  @SuppressWarnings("unchecked")
  @Override
  public List<NoCommentArticle> findAllArticles() {
    return this.sessionFactory.getCurrentSession().createSQLQuery("select * from Articles ")
            .addScalar("id", IntegerType.INSTANCE)
            .addScalar("title", StringType.INSTANCE)
            .addScalar("text", StringType.INSTANCE)
            .addScalar("author", StringType.INSTANCE)
            .setResultTransformer(new AliasToBeanResultTransformer(NoCommentArticle.class)).list();
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

  @SuppressWarnings("unchecked")
  @Override
  public List<NoCommentArticle> findAllArticlesWithText(String searchText){
    List<NoCommentArticle> searchTextArticle = findAllArticles();
    List<NoCommentArticle> helpList = new ArrayList<>();
    for (NoCommentArticle var : searchTextArticle)
    {
      if (var.getText().contains(searchText) || var.getAuthor().contains(searchText) || var.getTitle().contains(searchText)){
        helpList.add(var);
      }
    }
    return helpList;
  }

  @SuppressWarnings("unchecked")
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
