package com.mentoring.discolibrary.style;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StyleDTO {

    private Integer id;

    private String name;
}
