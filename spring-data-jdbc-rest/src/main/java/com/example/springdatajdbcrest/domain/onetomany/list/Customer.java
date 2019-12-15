package com.example.springdatajdbcrest.domain.onetomany.list;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import lombok.Value;
import lombok.With;

/**
 * One to Many example parent: [Parent] Customer -< [Child] Order List
 */
@Value
@With
public class Customer { // One to Many Parent

    private final @Id Long id;
    private final String name;
    private final List<Order> orders;

    public static Customer of(String name, List<Order> orders) {
        return new Customer(null, name, unmodifiableList(orders));
    }

    public static Customer of(String name) {
        return new Customer(null, name, unmodifiableList(new ArrayList<>()));
    }

    public Customer addOrder(int price) {
        // clone the list to make it immutable
        List<Order> orders_ = new ArrayList<>();
        orders_.addAll(orders);

        orders_.add(Order.of(price));
        return this.withOrders(orders_);
    }

    // setId equivalent in immutable object
    public Customer withIdNull() {
        return this.withId(null).withOrders(getOrdersWithNullId());
    }

    private List<Order> getOrdersWithNullId() {
        return orders.stream().map(order -> order.withId(null))
            .collect(Collectors.toList());
    }

}
