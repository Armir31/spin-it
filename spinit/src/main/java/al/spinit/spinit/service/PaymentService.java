package al.spinit.spinit.service;

import al.spinit.spinit.dto.CreatePaymentDto;
import al.spinit.spinit.entity.Payment;
import al.spinit.spinit.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<Payment> getList () {
        return paymentRepository.findAll();
    }
    public Payment getById (Long id){
        return  paymentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException
                        ("Payment" +id+ "not found!"));
    }
    public Payment create (CreatePaymentDto createPaymentDto) {
        Payment payment = modelMapper.map(createPaymentDto, Payment.class);
        return paymentRepository.save(payment);
    }
    public Payment update (CreatePaymentDto createPaymentDto, Long id){
        Payment existingPayment = getById(id);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(createPaymentDto, existingPayment);
        return paymentRepository.save(existingPayment);
    }
    public void delete(Long id){
        paymentRepository.deleteById(id);
    }
}
