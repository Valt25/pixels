package ru.simbirsoft.summerintensive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.simbirsoft.summerintensive.models.Pixel;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PixelListResponse {
    List<PixelDto> pixels;
    long rollback_time;
}