package ru.simbirsoft.summerintensive.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PutErrorDto {
    private long time_left;
}