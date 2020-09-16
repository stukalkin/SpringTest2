package hiberL4.domain;


import javax.persistence.*;

@Entity
@Table(name = "category_holder_all")
public class CategoryHolderCascadeAll {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    public CategoryHolderCascadeAll() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
