package com.practice.hospitalmanagement.Service;

import com.practice.hospitalmanagement.Entity.usersEntity.Users;
import com.practice.hospitalmanagement.Repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserCleanupService {
    private final UserRepository userRepository;

    public UserCleanupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Scheduled(cron = "0 0 3 * * ?") // every day at 3 AM
    public void deleteInactiveUsers() {
        LocalDateTime cutoff = LocalDateTime.now().minusMonths(4);

        List<Users> inactiveUsers =
                userRepository.findByLastActiveAtBefore(cutoff);

        userRepository.deleteAll(inactiveUsers);
    }
}
