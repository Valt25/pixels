package ru.simbirsoft.summerintensive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticResponse {
    long allCellsCount;
    long cellsCount;
    private long usersCount;
}