package com.microservice.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.userservice.dto.DepartmentDto;
import com.microservice.userservice.dto.ResponseDto;
import com.microservice.userservice.dto.UserDto;
import com.microservice.userservice.entity.User;
import com.microservice.userservice.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseDto getUser(Long userId) {
        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).get();
        UserDto userDto = mapToUser(user);

        ResponseEntity<DepartmentDto> responseEntity = restTemplate
        .getForEntity("http://localhost:8080/api/departments/" +
        user.getDepartmentId(),
        DepartmentDto.class);

        // ResponseEntity<DepartmentDto> responseEntity = restTemplate
        //         .getForEntity("http://DEPARTMENT-SERVICE/api/departments/" + user.getDepartmentId(),
        //                 DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();

        System.out.println(responseEntity.getStatusCode());

        responseDto.setUser(userDto);
        responseDto.setDepartment(departmentDto);

        return responseDto;
    }

    private UserDto mapToUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
