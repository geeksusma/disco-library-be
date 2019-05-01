package com.mentoring.discolibrary.style.get;

import com.mentoring.discolibrary.style.GetAllStylesDAO;
import com.mentoring.discolibrary.style.StyleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
class GetAllStylesController {
    private final GetAllStylesDAO getAllStylesDAO;
    private final StyleApiMapper styleApiMapper;

    GetAllStylesController(final GetAllStylesDAO getAllStylesDAO, final StyleApiMapper styleApiMapper) {

        this.getAllStylesDAO = getAllStylesDAO;
        this.styleApiMapper = styleApiMapper;
    }

    @GetMapping
    ResponseEntity<List<StyleDTO>> getAll() {

        final List<StyleDTO> dtos = getAllStylesDAO.getAllStyles().stream().map(v -> styleApiMapper.mapToDto(v)).collect(Collectors.toList());
        return new ResponseEntity(dtos, HttpStatus.OK);
    }

}

