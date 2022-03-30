package sk.ness.academy.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import sk.ness.academy.dto.Author;
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





}

