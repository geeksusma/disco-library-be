package com.mentoring.discolibrary.style;

public class StyleExistsDAO {

    private final StyleRepository styleRepository;

    StyleExistsDAO(final StyleRepository styleRepository) {

        this.styleRepository = styleRepository;
    }

    public boolean isStyleInTheSystem(final String style) {
        throw new UnsupportedOperationException("implement me!");
    }
}
