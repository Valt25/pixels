package ru.simbirsoft.summerintensive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatisticResponse {
    long allCellsCount;
    long cellsCount;
    List<PixelHistoryDto> history;
}
