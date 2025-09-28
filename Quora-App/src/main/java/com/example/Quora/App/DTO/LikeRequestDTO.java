package com.example.Quora.App.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeRequestDTO {

    @NotBlank(message = "Target ID cannot be blank")
    private int targetId;
    
    @NotBlank(message = "Target type cannot be blank")
    private String targetType;
    
    @NotNull(message = "isLike cannot be null")
    private Boolean isLike;

}
