package com.mentoring.discolibrary.style;

import java.util.List;

public class GetAllStylesDAO {

    private StyleRepository styleRepository;

    GetAllStylesDAO(StyleRepository styleRepository){
        this.styleRepository = styleRepository;
    }

    public List<Style> getAllStyles() {

        return styleRepository.findAllByOrderByName();
    }
}
