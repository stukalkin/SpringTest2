package hiber;

import javax.persistence.*;

@Entity
@Table(name = "person")  //можно опустить, если сходится с названием таблицы
public class Person {
    @Id
    @GeneratedValue //генерирует значение сама база данных
    private Long id;
    @Column(name = "firstname")  //можно опустить, если сходится с названием столбца
    private String firstname;
    @Column(name = "lastname")  //можно опустить, если сходится с названием столбца
    private String lastname;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


}