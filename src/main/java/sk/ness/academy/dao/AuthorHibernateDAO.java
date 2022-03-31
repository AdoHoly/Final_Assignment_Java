package sk.ness.academy.dao;

import java.util.*;

import javax.annotation.Resource;
import javax.persistence.Tuple;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.Author;
import sk.ness.academy.dto.AuthorStats;
import sk.ness.academy.dto.NoCommentArticle;

@Repository
public class AuthorHibernateDAO implements AuthorDAO {

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  @SuppressWarnings("unchecked")
  @Override
  public List<Author> findAll() {
    return this.sessionFactory.getCurrentSession().createSQLQuery("select distinct a.author as name from articles a ")
        .addScalar("name", StringType.INSTANCE)
        .setResultTransformer(new AliasToBeanResultTransformer(Author.class)).list();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<AuthorStats> findAuthorsWithCount() {
    return this.sessionFactory.getCurrentSession().createQuery("select new sk.ness.academy.dto.AuthorStats(author, count(*)) from Article group by author ").stream().toList();

  }

}

