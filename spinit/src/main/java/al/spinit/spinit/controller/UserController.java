package al.spinit.spinit.controller;

import al.spinit.spinit.dto.CreateUserDto;
import al.spinit.spinit.dto.UserDto;
import al.spinit.spinit.entity.User;
import al.spinit.spinit.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/api/user")
public class UserController {

    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<UserDto> create (@RequestBody CreateUserDto createUserDto){
        log.info("Creating user");
        User user = this.userService.create(createUserDto);
        return new ResponseEntity<>(this.modelMapper.map(user, UserDto.class),HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<User>> getList(){
        log.info("Getting list of users");
        return ResponseEntity.ok(userService.getList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id){
        User user = userService.getById(id);
        return new ResponseEntity<>(this.modelMapper.map(user, UserDto.class), HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody CreateUserDto updateUserDto){
        User saved = userService.update(updateUserDto, id);
        return new ResponseEntity<>(modelMapper.map(saved, UserDto.class), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id){
        log.warn("Deleting user with ID: " + id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
