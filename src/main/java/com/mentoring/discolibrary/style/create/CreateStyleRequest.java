package com.mentoring.discolibrary.style.create;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
class CreateStyleRequest {

    @NotBlank(message = "Name for the new style must be mandatory")
    @Size(max = 15, message = "Name too long")
    private String name;
}
