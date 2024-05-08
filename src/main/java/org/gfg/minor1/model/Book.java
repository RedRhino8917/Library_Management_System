package org.gfg.minor1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String name;

    private String bookNo;

    private int cost;

    @Enumerated
    private BookType type;

    @ManyToOne                  // telling the association
    @JoinColumn               // pushing the data inside the book table (student id)
    private Student student;    // by default the book ID will be added to the student table

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("bookList")
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Txn> txnList;

    private int paidAmount;


}
