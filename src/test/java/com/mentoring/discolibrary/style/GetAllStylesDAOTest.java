package com.mentoring.discolibrary.style;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class GetAllStylesDAOTest {

    private GetAllStylesDAO getAllStylesDAO;
    private StyleRepository styleRepository;

    @Before
    public void setUp() {
        getAllStylesDAO = new GetAllStylesDAO();
    }

    @Test
    public void should_findAllStyles_when_getAll() {
        //when
        getAllStylesDAO.getAllStyles();

        //then
        then(styleRepository).should().findAllByOrderByName();
    }

    @Test
    public void should_returnStyles_when_stylesExist() {

        //given
        final Style oneStyle = mock(Style.class);
        given(styleRepository.findAllByOrderByName()).willReturn(Collections.singletonList(oneStyle));

        //when
        final List<Style> allStyles = getAllStylesDAO.getAllStyles();

        //then
        assertThat(allStyles).containsOnly(oneStyle);
    }
}