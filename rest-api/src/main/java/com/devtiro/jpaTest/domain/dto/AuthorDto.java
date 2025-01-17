package com.devtiro.jpaTest.domain.dto;

// It called Pojo ( Plain old Java Objects )

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {

    private Long id ;

    private String name;

    private Integer age;

}
