package hiber.hw;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyers;

    public Product() {}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public Double getPrice() {return price;}
    public void setPrice(Double price) {this.price = price;}
    public Buyer getBuyers() {return buyers;}
    public void setBuyers(Buyer buyers) {this.buyers = buyers;}
}
