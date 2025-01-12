package com.devtiro.database.domain;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {


    private Long id ;

    private String name;

    private Integer age;

}
