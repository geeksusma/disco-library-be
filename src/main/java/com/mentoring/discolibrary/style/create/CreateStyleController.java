package com.mentoring.discolibrary.style.create;

import com.mentoring.discolibrary.style.StyleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

class CreateStyleController {

    private final CreateSkillApiMapper createSkillApiMapper;
    private final CreateStyleService createStyleService;

    CreateStyleController(final CreateSkillApiMapper createSkillApiMapper, final CreateStyleService createStyleService) {

        this.createSkillApiMapper = createSkillApiMapper;
        this.createStyleService = createStyleService;
    }

    ResponseEntity<StyleDTO> createStyle(@Valid @RequestBody final CreateStyleRequest request) {
        throw new UnsupportedOperationException("implement me!");
    }
}
