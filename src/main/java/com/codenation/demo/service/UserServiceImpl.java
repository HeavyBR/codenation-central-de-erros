package com.codenation.demo.service;

import com.codenation.demo.model.User;
import com.codenation.demo.model.request.UserRequest;
import com.codenation.demo.model.response.UserResponse;
import com.codenation.demo.repository.UserRepository;
import com.codenation.demo.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Long save(UserRequest user) {
        User createdUser = repository.save(User.valueOf(user));
        return createdUser.getId();
    }

    public UserResponse findById(Long id) throws ChangeSetPersister.NotFoundException {
        User user = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return UserResponse.valueOf(user);
    }

    @Override
    public List<UserResponse> findAll() {
        return UserResponse.valueOf(repository.findAll());
    }

    @Transactional
    public void update(UserRequest userRequest, Long id) throws ChangeSetPersister.NotFoundException {
        User user = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        user.setPassword(userRequest.getPassword());
        user.setUsername(userRequest.getUsername());
        repository.save(user);
    }

    @Transactional
    public void delete(Long id) throws ChangeSetPersister.NotFoundException {
        User user = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        repository.delete(user);
    }


}
