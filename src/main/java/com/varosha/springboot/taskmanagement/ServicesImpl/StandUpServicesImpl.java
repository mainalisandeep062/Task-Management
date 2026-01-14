package com.varosha.springboot.taskmanagement.ServicesImpl;

import com.varosha.springboot.taskmanagement.Configuration.ExtractEmail;
import com.varosha.springboot.taskmanagement.DTO.standUp.StandUpRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.standUp.StandUpResponseDTO;
import com.varosha.springboot.taskmanagement.Models.StandUp;
import com.varosha.springboot.taskmanagement.Models.User;
import com.varosha.springboot.taskmanagement.Repository.StandUpRepo;
import com.varosha.springboot.taskmanagement.Repository.UserRepo;
import com.varosha.springboot.taskmanagement.Services.StandUpServices;
import com.varosha.springboot.taskmanagement.converter.StandUpConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StandUpServicesImpl implements StandUpServices {

    private final StandUpRepo standUpRepo;
    private final UserRepo userRepo;
    private final ExtractEmail extract;
    private final StandUpConverter converter;

    @Override
    public StandUpResponseDTO submitStandUp(StandUpRequestDTO request) {
        User user = userRepo.findByEmail(extract.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (standUpRepo.existsByUserAndSubmissionDate(user, LocalDate.now()))
            throw new RuntimeException("Standup already submitted for today!");

        StandUp standUp = converter.toEntity(request);
        standUp.setSubmissionDate(LocalDate.now());
        standUp.setUser(user);

        standUpRepo.save(standUp);
        return converter.toDto(standUp);
    }
}