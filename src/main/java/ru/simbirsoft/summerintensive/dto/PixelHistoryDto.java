package ru.simbirsoft.summerintensive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.simbirsoft.summerintensive.models.Pixel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PixelHistoryDto {
    private int x;
    private int y;
    private String color;
    private LocalDateTime creationTime;

    public static PixelHistoryDto from(Pixel pixel) {
        return PixelHistoryDto.builder()
                .x(pixel.getX())
                .y(pixel.getY())
                .color(pixel.getColor())
                .creationTime(pixel.getCreated())
                .build();
    }

    public static List<PixelHistoryDto> from(List<Pixel> pixels) {
        return pixels.stream()
                .map(PixelHistoryDto::from)
                .collect(Collectors.toList());
    }

}
