package br.com.dogins.dto.response;

import org.bson.types.Binary;

import java.util.List;

public record ProductCardResponseDto(
        String id,
        List<Binary> productImages,
        String productPrice,
        String productStock,
        String productName
) {
}
