package com.mentoring.discolibrary.style.get;

import com.mentoring.discolibrary.style.Style;
import com.mentoring.discolibrary.style.StyleDTO;
import org.springframework.stereotype.Component;

@Component
class StyleApiMapper {
    StyleDTO mapToDto(final Style oneStyle) {
        return StyleDTO.builder().id(oneStyle.getId())
                .name(oneStyle.getName())
                .build();
    }
}
