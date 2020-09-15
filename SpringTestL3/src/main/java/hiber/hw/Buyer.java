package hiber.hw;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buyers")
public class Buyer {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    @Column(name = "first_name")
    private String firstname;

    @OneToMany(mappedBy = "buyers")
    private List<Product> products;

    public Buyer() {}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getFirstname() {return firstname;}
    public void setFirstname(String firstname) {this.firstname = firstname;}
    public List<Product> getProducts() {return products;}
    public void setProducts(List<Product> products) {this.products = products;}
}
