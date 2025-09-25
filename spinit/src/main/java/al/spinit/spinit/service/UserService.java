package al.spinit.spinit.service;

import al.spinit.spinit.dto.CreateUserDto;
import al.spinit.spinit.entity.User;
import al.spinit.spinit.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<User> getList () {
        return userRepository.findAll();
    }
    public User getById (Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                        ("User with ID" + id + "not found!"));
    }
    public void deleteById (Long id) {
        userRepository.deleteById(id);
    }
    public User create (CreateUserDto createUserDto) {
        User user = modelMapper.map(createUserDto, User.class);
        return userRepository.save(user);
    }
    public User update (CreateUserDto createUserDto, Long id) {
        User existingUser = getById(id);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(createUserDto, existingUser);
        return userRepository.save(existingUser);
    }


}
