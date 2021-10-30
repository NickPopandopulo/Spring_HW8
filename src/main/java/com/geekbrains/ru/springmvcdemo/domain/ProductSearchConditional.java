package com.geekbrains.ru.springmvcdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchConditional {

    private Integer minPrice;
    private Integer maxPrice;


}
