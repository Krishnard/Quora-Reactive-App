package com.example.Quora.App.Services;

import com.example.Quora.App.DTO.LikeRequestDTO;
import com.example.Quora.App.DTO.LikeResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ILikeService {
    
     Mono<LikeResponseDTO> createLike(LikeRequestDTO likeRequestDTO);
    
     Mono<LikeResponseDTO> countLikeByTargetIdAndType(int targetId, String targetType);
    
     Mono<LikeResponseDTO> countDisLikesByTargetIdAndType(int targetId, String targetType);
    
     Mono<LikeResponseDTO> toggleLike(String targetType, int targetId, Boolean isLike);
}
