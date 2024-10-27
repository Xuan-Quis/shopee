package com.actvn.Shopee_BE.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponse {
    private List<ProductItemResponse> categories;

    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;
    private boolean firstPage;
}
