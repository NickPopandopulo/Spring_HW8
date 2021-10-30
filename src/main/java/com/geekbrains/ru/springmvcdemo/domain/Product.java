package com.geekbrains.ru.springmvcdemo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@ToString(exclude = {"categories", "customers"})
@EqualsAndHashCode(exclude = {"categories", "customers"})
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private int price;

    @Column(name = "image_link")
    private String imageLink;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();;

//    @ManyToMany
//    @JoinTable(
//            name = "product_customer",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "customer_id")
//    )
    @OneToMany(mappedBy = "product")
    private Set<PurchaseDetails> customers = new HashSet<>();

}
