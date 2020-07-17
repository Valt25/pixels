package ru.simbirsoft.summerintensive.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PutErrorDto {
    private String error = "You can`t do it";
    private long time_left;
}