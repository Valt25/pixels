package ru.simbirsoft.summerintensive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.simbirsoft.summerintensive.models.Pixel;
import ru.simbirsoft.summerintensive.models.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PixelDto {
    private int x;
    private int y;
    private String color;

    public static PixelDto from(Pixel pixel) {
        return PixelDto.builder()
                .x(pixel.getX())
                .y(pixel.getY())
                .color(pixel.getColor())
                .build();
    }

    public static List<PixelDto> from(List<Pixel> pixels) {
        return pixels.stream()
                .map(PixelDto::from)
                .collect(Collectors.toList());
    }

}