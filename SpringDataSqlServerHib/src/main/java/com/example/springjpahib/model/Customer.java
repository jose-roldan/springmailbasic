package com.example.springjpahib.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
    @GenericGenerator(
    name = "customer_number",
    strategy = "sequence",
    parameters = {
        @Parameter(name = "sequence_name", value = "testeschema.customer_number"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "5"),
        @Parameter(name = "optimizer", value = "pooled-lo")
    }
)
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "customer_first_name")
    private String firstName;
    @Column(name = "customer_last_name")
    private String lastName;

}
