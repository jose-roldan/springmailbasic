package com.example.springjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customer_tbl", schema = "testeschema")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_number" )
    @SequenceGenerator(name = "customer_number", sequenceName = "testeschema.customer_number", allocationSize = 5)
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "customer_first_name")
    private String firstName;
    @Column(name = "customer_last_name")
    private String lastName;

}
