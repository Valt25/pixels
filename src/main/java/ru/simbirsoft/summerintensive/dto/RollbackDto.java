package ru.simbirsoft.summerintensive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class RollbackDto {
    public RollbackDto (long time) {
        rollback_time = time;
    }
    public long rollback_time;
}