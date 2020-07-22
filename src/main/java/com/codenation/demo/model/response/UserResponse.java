package com.codenation.demo.model.response;

import com.codenation.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String userName;
    private LocalDateTime createdAt;

    public static UserResponse valueOf(User user) {
        return UserResponse.builder()
                .userName(user.getUsername())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static List<UserResponse> valueOf(List<User> user) {
        return user.stream().map(UserResponse::valueOf).collect(Collectors.toList());
    }
}
