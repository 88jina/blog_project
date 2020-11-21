package com.example.test;

import com.example.model.RoleType;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    //{id}주소로 파라미터를 전달 받을 수 있음
    //http://localhost:8090/blog/dummy/user/2
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        User user =userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException(id+"번 유저 정보가 없습니다");
            }
        });
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user) {
        System.out.println("username : "+ user.getUsername());
        System.out.println("password" + user.getPassword());
        System.out.println("createDate : "+user.getCreateDate());
        System.out.println("role : "+user.getRole());
        user.setRole(RoleType.USER);


        userRepository.save(user);


        return "회원가입 완료";
    }

    @GetMapping("/dummy/user")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user/page/{page}")
    public Page<User> pageList
            (@PageableDefault
                     (size=2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> users =userRepository.findAll(pageable);
        return users;
    }
    @PutMapping("/dummy/user/{id}")
    public User updateUser(){

    }
}
