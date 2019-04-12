package com.mentoring.discolibrary.style.get;

import com.mentoring.discolibrary.style.GetAllStylesDAO;
import com.mentoring.discolibrary.style.Style;
import com.mentoring.discolibrary.style.StyleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

class GetAllStylesController {
    private final GetAllStylesDAO getAllStylesDAO;
    private final StyleApiMapper styleApiMapper;

    GetAllStylesController(final GetAllStylesDAO getAllStylesDAO, final StyleApiMapper styleApiMapper) {

        this.getAllStylesDAO = getAllStylesDAO;
        this.styleApiMapper = styleApiMapper;
    }

    ResponseEntity<List<StyleDTO>> getAll() {
        List<Style> allStyles = getAllStylesDAO.getAllStyles();
        List<StyleDTO> respuesta = new ArrayList<>();

     /*   if (!allStyles.isEmpty()) {
            for (Style style : allStyles) {
                respuesta.add(styleApiMapper.mapToDto(style));
            }

            return new ResponseEntity(respuesta,HttpStatus.OK);
        }
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }*/

        for (Style style : allStyles) {
            respuesta.add(styleApiMapper.mapToDto(style));
        }

        return new ResponseEntity(respuesta, HttpStatus.OK);
    }

}

