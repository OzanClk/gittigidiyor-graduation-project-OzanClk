package dev.patika.creditscore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorsResponseEntity {

    private String message;
    private int statusCode;

}

