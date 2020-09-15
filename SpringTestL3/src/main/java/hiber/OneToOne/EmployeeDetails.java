package hiber.OneToOne;

import javax.persistence.*;

@Entity
@Table(name = "employee_details")
public class EmployeeDetails {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToOne(mappedBy = "details")
    private Employee employee;

}
