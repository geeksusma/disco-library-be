package com.mentoring.discolibrary.style.get;

import com.mentoring.discolibrary.style.GetAllStylesDAO;
import com.mentoring.discolibrary.style.Style;
import com.mentoring.discolibrary.style.StyleDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class GetAllStylesControllerTest {

    private GetAllStylesController getAllStylesController;
    @Mock
    private GetAllStylesDAO getAllStylesDAO;
    @Mock
    private StyleApiMapper styleApiMapper;

    @Before
    public void setUp() {
        getAllStylesController = new GetAllStylesController(getAllStylesDAO, styleApiMapper);
    }

    @Test
    public void should_getStyles_when_getAll() {
        //when
        getAllStylesController.getAll();

        //then
        then(getAllStylesDAO).should().getAllStyles();
    }

    @Test
    public void should_mapStylesToDto_when_thereAreStyles() {
        //given
        final Style oneStyle = setUpStyleInSystem();

        //when
        getAllStylesController.getAll();

        //then
        then(styleApiMapper).should().mapToDto(oneStyle);
    }

    private Style setUpStyleInSystem() {
        final Style oneStyle = mock(Style.class);
        given(getAllStylesDAO.getAllStyles()).willReturn(Collections.singletonList(oneStyle));
        return oneStyle;
    }

    @Test
    public void should_returnHttpOK_when_getAllStyles() {
        //when
        final ResponseEntity<List<StyleDTO>> response = getAllStylesController.getAll();

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void should_returnMappedStyles_when_thereAreStylesToReturn() {
        //given
        final Style oneStyle = setUpStyleInSystem();
        final StyleDTO expectedStyle = StyleDTO.builder().build();
        given(styleApiMapper.mapToDto(oneStyle)).willReturn(expectedStyle);

        //when
        final ResponseEntity<List<StyleDTO>> response = getAllStylesController.getAll();

        //then
        assertThat(response.getBody()).containsOnly(expectedStyle);

    }
}