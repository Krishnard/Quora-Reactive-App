package com.example.Quora.App.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeResponseDTO {
    
    private int id;
    
    private int targetId;
    
    private String targetType;
    
    private Boolean isLike;
    
    private LocalDateTime createdAt;
    
}
