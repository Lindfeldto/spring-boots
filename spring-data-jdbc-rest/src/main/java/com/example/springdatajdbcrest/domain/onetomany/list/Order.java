package com.example.springdatajdbcrest.domain.onetomany.list;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Value;
import lombok.With;

/**
 * One to Many example child: [Parent] Customer -< [Child] Order List
 */
@Value
@With
@Table("orders") // to avoid order which is SQL reserved keyword
public class Order { // One to Many Child

    private final @Id Long id;
    private final int price;

    public static Order of(int price) {
        return new Order(null, price);
    }

}
