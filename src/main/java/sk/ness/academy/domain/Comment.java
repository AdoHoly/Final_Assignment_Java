package sk.ness.academy.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@SequenceGenerator(name = "comments_seq_store", sequenceName = "comment_seq", allocationSize = 1)
public class Comment {

    public Comment() {
        this.createTimestamp = new Date();
    }

    @Id
    @Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_seq_store")
    private Integer id;

    @Column(name = "text", length = 2000)
    private String text;

    @Column(name = "author", length = 250)
    private String author;

    @Column(name = "create_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTimestamp;

    @Column(name = "article_comment")
    private Integer article_id;

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Integer getId() { return id; }

    public String getText() { return text; }

    public String getAuthor() { return author; }

    public Date getCreateTimestamp() { return createTimestamp; }

    public void setId(Integer id) { this.id = id; }

    public void setText(String text) { this.text = text; }

    public void setAuthor(String author) { this.author = author; }

    public void setCreateTimestamp(Date createTimestamp) { this.createTimestamp = createTimestamp; }
}
