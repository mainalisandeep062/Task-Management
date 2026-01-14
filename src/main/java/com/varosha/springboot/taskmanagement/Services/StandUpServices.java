package com.varosha.springboot.taskmanagement.Services;

import com.varosha.springboot.taskmanagement.DTO.standUp.StandUpRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.standUp.StandUpResponseDTO;

public interface StandUpServices {
    StandUpResponseDTO submitStandUp(StandUpRequestDTO request);
}