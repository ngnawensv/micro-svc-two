package com.belrose.microsvctwo.pojo;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person implements Serializable {
    private long id;
    @NonNull
    private String lastName;
    private String firstName;
}
