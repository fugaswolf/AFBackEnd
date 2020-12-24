package ehb.alvi.afbackendapi.dto;

import ehb.alvi.afbackendapi.entity.Address;
import ehb.alvi.afbackendapi.entity.Customer;
import ehb.alvi.afbackendapi.entity.Order;
import ehb.alvi.afbackendapi.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
