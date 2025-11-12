package al.spinit.spinit.service;

import al.spinit.spinit.dto.CreateOrderDto;
import al.spinit.spinit.dto.CreateOrderItemDto;
import al.spinit.spinit.entity.Order;
import al.spinit.spinit.entity.OrderItem;
import al.spinit.spinit.entity.OrderStatus;
import al.spinit.spinit.entity.Product;
import al.spinit.spinit.entity.User;
import al.spinit.spinit.repository.OrderItemRepository;
import al.spinit.spinit.repository.OrderRepository;
import al.spinit.spinit.repository.ProductRepository;
import al.spinit.spinit.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    public static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<Order> getList(){
        return orderRepository.findAll();
    }

    public void delete (Long id){
        orderRepository.deleteById(id);
    }
    public Order getById (Long id){
        return orderRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException
                        ("Order with ID" + id + "not found!"));
    }
    public Order create (CreateOrderDto createOrderDto){
        if (createOrderDto == null){
            throw new IllegalArgumentException("Order DTO cannot be null");
        }
        if (createOrderDto.getUserId() == null){
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (createOrderDto.getOrderItems() == null | createOrderDto.getOrderItems().isEmpty()){
            throw new IllegalArgumentException("Order items cannot be null or empty");
        }
        User existingUser = userRepository.findById(createOrderDto.getUserId())
                .orElseThrow(()->new EntityNotFoundException
                        ("User with ID" + createOrderDto.getUserId() + "not found!"));

        Order order = new Order();
        order.setUsers(existingUser);
        order.setTotalAmount(0D);
        order.setOrderStatus(OrderStatus.PROCESSING);

        Order savedOrder = orderRepository.save(order);

        Set<Long> ids = createOrderDto.getOrderItems().stream()
                .map(CreateOrderItemDto::getProductId)
                .collect(Collectors.toSet());

        List<Product> products = this.productRepository.findAllById(ids);

        Map<Long, Double> productPriceMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Product::getPrice));

        Double totalAmount = 0D;

        for (CreateOrderItemDto createOrderItemDto : createOrderDto.getOrderItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(createOrderItemDto.getProductId());
            orderItem.setPrice(productPriceMap.get(createOrderItemDto.getProductId()));
            orderItem.setSubTotal(createOrderItemDto.getQuantity() * orderItem.getPrice());
            orderItem.setQuantity(createOrderItemDto.getQuantity());
            orderItem.setOrder(savedOrder);
            totalAmount += orderItem.getSubTotal();
            orderItemRepository.save(orderItem);
        }
        savedOrder.setTotalAmount(totalAmount);
        return orderRepository.save(savedOrder);
    }
    public Order update (Long id, CreateOrderDto updateOrderDto){
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException
                        ("Order with ID" + id + "not found!"));
        if (existingOrder.getOrderStatus() != OrderStatus.PROCESSING){
            throw new IllegalArgumentException("Order cannot be updated if it is not in PROCESSING status");
        }
        orderItemRepository.deleteAll(existingOrder.getOrderItems());
        existingOrder.getOrderItems().clear();
        Set<Long> ids = updateOrderDto.getOrderItems().stream()
                .map(CreateOrderItemDto :: getProductId)
                .collect(Collectors.toSet());
        List<Product> products = this.productRepository.findAllById(ids);
        Map<Long, Double> priceMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Product::getPrice));
        Double totalAmount = 0D;
        for (CreateOrderItemDto createOrderItemDto: updateOrderDto.getOrderItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(orderItem.getProductId());
            orderItem.setQuantity(orderItem.getQuantity());
            orderItem.setPrice(orderItem.getPrice());
            orderItem.setSubTotal(orderItem.getPrice() * orderItem.getQuantity());
            orderItem.setOrder(existingOrder);
            totalAmount += orderItem.getSubTotal();
            orderItemRepository.save(orderItem);
        }
        existingOrder.setTotalAmount(totalAmount);
        return orderRepository.save(existingOrder);
    }
    public List<Order> findOrdersByUserId(Long userId){
        User user = new User();
        user.setId(userId);
        return orderRepository.findOrdersByUsers(user);
    }
    public Page<Order> findOrdersByOrderStatus(OrderStatus orderStatus,
                                               Integer size, Integer page,
                                               String sort, Sort.Direction direction){
        Sort sortBy = Sort.by(direction, sort);
        Pageable pageable = PageRequest.of(page, size, sortBy);
        return orderRepository.findOrdersByOrderStatus(orderStatus, pageable);
    }
}
