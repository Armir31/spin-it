package al.spinit.spinit.controller;

import al.spinit.spinit.dto.CreatePaymentDto;
import al.spinit.spinit.dto.PaymentDto;
import al.spinit.spinit.entity.Payment;
import al.spinit.spinit.service.PaymentService;
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
@RequestMapping("/api/payments")
public class PaymentController {

    public static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody CreatePaymentDto createPaymentDto){
        log.info("Creating payment...");
        Payment payment = paymentService.create(createPaymentDto);
        return new ResponseEntity<>(modelMapper.map(payment, PaymentDto.class), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<PaymentDto>> getList(){
        log.info("Getting list of payments...");
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable Long id){
        Payment payment = paymentService.getById(id);
        return new ResponseEntity<>(modelMapper.map(payment, PaymentDto.class), HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<PaymentDto> updatePayment(
            @PathVariable Long id, @RequestBody CreatePaymentDto updatePaymentDto){
        log.info("Updating payment...");
        Payment saved = paymentService.update(updatePaymentDto, id);
        return new ResponseEntity<>(modelMapper.map(saved, PaymentDto.class), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id){
        log.warn("Deleting product with ID: " + id);
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
