package com.mentoring.discolibrary.style.get;

import com.mentoring.discolibrary.style.Style;
import com.mentoring.discolibrary.style.StyleDTO;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


public class StyleApiMapperTest {

    private StyleApiMapper styleApiMapper;

    @Before
    public void setUp() {
        styleApiMapper = new StyleApiMapper();
    }

    @Test
    public void should_mapToExpectedDTO_when_mapToDTO() {
        //given
        final Integer styleId = 1;
        final String name = "Rock";

        final Style styleToMap = mock(Style.class);
        given(styleToMap.getId()).willReturn(styleId);
        given(styleToMap.getName()).willReturn(name);

        final StyleDTO expectedStyle = StyleDTO.builder()
                .id(styleId)
                .name(name)
                .build();
        //when
        final StyleDTO mappedStyle = styleApiMapper.mapToDto(styleToMap);


        //then
        assertThat(mappedStyle).isEqualToComparingFieldByField(expectedStyle);
    }
}