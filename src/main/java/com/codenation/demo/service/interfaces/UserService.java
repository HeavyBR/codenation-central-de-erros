package com.codenation.demo.service.interfaces;

import com.codenation.demo.model.request.UserRequest;
import com.codenation.demo.model.response.UserResponse;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.awt.print.Pageable;
import java.util.List;

public interface UserService {

   Long save(UserRequest user);
   List<UserResponse> findAll();
   void update(UserRequest user, Long id) throws ChangeSetPersister.NotFoundException;
   void delete(Long id) throws ChangeSetPersister.NotFoundException;
}
