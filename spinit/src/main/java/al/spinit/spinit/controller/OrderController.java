package al.spinit.spinit.controller;

import al.spinit.spinit.dto.CreateOrderDto;
import al.spinit.spinit.dto.OrderDto;
import al.spinit.spinit.entity.Order;
import al.spinit.spinit.service.OrderService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    public static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        log.info("Creating order...");
        Order order = orderService.create(createOrderDto);
        return new ResponseEntity<>(modelMapper.map(order, OrderDto.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getList() {
        log.info("Getting list of orders...");
        return ResponseEntity.ok(orderService.getList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable Long id) {
        Order order = orderService.getById(id);
        return new ResponseEntity<>(modelMapper.map(order, OrderDto.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id,
                                                @RequestBody CreateOrderDto updateOrderDto) {
        log.info("Updating order...");
        Order saved = orderService.update(id, updateOrderDto);
        return new ResponseEntity<>(modelMapper.map(saved, OrderDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        log.warn("Deleting order with ID " + id);
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}