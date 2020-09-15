package hiber.ManyToMany;


import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    @Column(name = "title")
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)  //не подтягивает за собой все зависимости,
                                         // их можно получить путем отдельного запроса
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Category() {
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
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
