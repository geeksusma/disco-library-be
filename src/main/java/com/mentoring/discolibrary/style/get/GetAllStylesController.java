package com.mentoring.discolibrary.style.get;

import com.mentoring.discolibrary.style.GetAllStylesDAO;
import com.mentoring.discolibrary.style.StyleDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

class GetAllStylesController {
    private final GetAllStylesDAO getAllStylesDAO;
    private final StyleApiMapper styleApiMapper;

    GetAllStylesController(final GetAllStylesDAO getAllStylesDAO, final StyleApiMapper styleApiMapper) {

        this.getAllStylesDAO = getAllStylesDAO;
        this.styleApiMapper = styleApiMapper;
    }

    ResponseEntity<List<StyleDTO>> getAll() {

        throw new UnsupportedOperationException("implement me!");
    }
}
