package com.varosha.springboot.taskmanagement.converter;

import com.varosha.springboot.taskmanagement.DTO.standUp.StandUpResponseDTO;
import com.varosha.springboot.taskmanagement.Models.StandUp;
import com.varosha.springboot.taskmanagement.DTO.standUp.StandUpRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class StandUpConverter {
    public StandUp toEntity (StandUpRequestDTO requestDto){
        if(requestDto == null)
            return null;
        StandUp standUp = new StandUp();
        standUp.setAccomplishedYesterday(requestDto.getAccomplishedYesterday());
        standUp.setPlanForToday(requestDto.getPlanForToday());
        standUp.setBlockers(requestDto.getBlockers());

        return standUp;
    }

    public StandUpResponseDTO toDto(StandUp standUp){
        StandUpResponseDTO response = new StandUpResponseDTO();
        response.setId(standUp.getId());
        response.setAccomplishedYesterday(standUp.getAccomplishedYesterday());
        response.setPlanForToday(standUp.getPlanForToday());
        response.setBlockers(standUp.getBlockers());
        response.setUserId(standUp.getUser().getId());
        response.setSubmittedDate(standUp.getSubmissionDate());

        return response;
    }
}
