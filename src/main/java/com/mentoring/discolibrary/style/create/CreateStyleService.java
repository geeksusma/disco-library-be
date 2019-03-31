package com.mentoring.discolibrary.style.create;

import com.mentoring.discolibrary.style.CreateStyleDAO;
import com.mentoring.discolibrary.style.StyleDomain;
import com.mentoring.discolibrary.style.StyleExistsDAO;

class CreateStyleService {

    private final StyleExistsDAO styleExistsDAO;
    private final CreateStyleDAO createStyleDAO;

    CreateStyleService(final StyleExistsDAO styleExistsDAO, final CreateStyleDAO createStyleDAO) {

        this.styleExistsDAO = styleExistsDAO;
        this.createStyleDAO = createStyleDAO;
    }

    StyleDomain createStyle(final CreateStyle createStyle) {
        throw new UnsupportedOperationException("implement me!");
    }
}
