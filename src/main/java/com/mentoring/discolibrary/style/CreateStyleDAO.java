package com.mentoring.discolibrary.style;

import com.mentoring.discolibrary.style.create.CreateStyle;

public class CreateStyleDAO {

    private final CreateStyleModelMapper createStyleModelMapper;
    private final StyleRepository styleRepository;

    CreateStyleDAO(final CreateStyleModelMapper createStyleModelMapper, final StyleRepository styleRepository) {

        this.createStyleModelMapper = createStyleModelMapper;
        this.styleRepository = styleRepository;
    }

    public StyleDomain createStyle(final CreateStyle createStyle) {
        throw new UnsupportedOperationException("implement me!");
    }
}
