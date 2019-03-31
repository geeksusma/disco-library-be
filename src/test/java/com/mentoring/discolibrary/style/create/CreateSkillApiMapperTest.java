package com.mentoring.discolibrary.style.create;

import com.mentoring.discolibrary.style.StyleDTO;
import com.mentoring.discolibrary.style.StyleDomain;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CreateSkillApiMapperTest {

    private CreateSkillApiMapper createSkillApiMapper;

    @Before
    public void setUp() {
        createSkillApiMapper = new CreateSkillApiMapper();
    }

    @Test
    public void should_mapToExpected_when_mapToDomain() {
        //given

        final String styleName = "Punk";
        final CreateStyleRequest request = CreateStyleRequest.builder()
                .name(styleName)
                .build();
        final CreateStyle expectedStyle = CreateStyle.builder()
                .name(styleName)
                .build();

        //when
        final CreateStyle createStyle = createSkillApiMapper.mapToDomain(request);

        //then
        assertThat(createStyle).isEqualToComparingFieldByField(expectedStyle);
    }

    @Test
    public void should_mapToExpected_when_mapToDto() {
        //given
        final Integer styleId = 12;
        final String style = "Punk";

        final StyleDomain createdStyle = StyleDomain.builder().id(styleId)
                .name(style)
                .build();

        final StyleDTO expectedStyle = StyleDTO.builder()
                .id(styleId)
                .name(style)
                .build();

        //when
        final StyleDTO styleDTO = createSkillApiMapper.mapToDto(createdStyle);

        //then
        assertThat(styleDTO).isEqualToComparingFieldByField(expectedStyle);
    }

}