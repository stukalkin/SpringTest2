package hiberL4.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "Article.findAll", query = "SELECT a from Article a"),
        @NamedQuery(name = "Article.findByLowerId", query = "SELECT a from Article a where a.id < :id")
})
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_lazy_id")
    private Category categoryLazy;


    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategoryLazy() {
        return categoryLazy;
    }

    public void setCategoryLazy(Category categoryLazy) {
        this.categoryLazy = categoryLazy;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", category=" + category +
                ", categoryLazy=" + categoryLazy +
                '}';
    }
}
