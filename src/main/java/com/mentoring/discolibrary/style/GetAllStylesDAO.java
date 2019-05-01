package com.mentoring.discolibrary.style;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllStylesDAO {

    private StyleRepository styleRepository;

    GetAllStylesDAO(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public List<Style> getAllStyles() {

        return styleRepository.findAllByOrderByNameAsc();
    }
}
