package al.spinit.spinit.repository;

import al.spinit.spinit.entity.Order;
import al.spinit.spinit.entity.OrderStatus;
import al.spinit.spinit.entity.Role;
import al.spinit.spinit.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUsers(User user);
    Page<Order> findOrdersByOrderStatus(OrderStatus orderStatus, Pageable pageable);
}
