package com.mentoring.discolibrary.style;

import com.mentoring.discolibrary.style.create.CreateStyle;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CreateStyleModelMapperTest {

    private CreateStyleModelMapper createStyleModelMapper;

    @Before
    public void setUp() {
        createStyleModelMapper = new CreateStyleModelMapper();
    }

    @Test
    public void should_mapToExpected_when_mapToDomain() {
        //given

        final String styleName = "Punk";
        final Integer styleId = 1231;
        final StyleEntity styleEntity = StyleEntity.builder()
                .name(styleName)
                .id(styleId)
                .build();

        final StyleDomain expectedStyle = StyleDomain.builder()
                .name(styleName)
                .id(styleId)
                .build();

        //when
        final StyleDomain createStyle = createStyleModelMapper.mapToDomain(styleEntity);

        //then
        assertThat(createStyle).isEqualToComparingFieldByField(expectedStyle);
    }

    @Test
    public void should_mapToExpected_when_mapToDto() {
        //given
        final String style = "Punk";

        final CreateStyle styleToCreate = CreateStyle.builder().name(style).build();

        final StyleEntity expectedStyle = StyleEntity.builder()
                .name(style)
                .build();

        //when
        final StyleEntity styleEntity = createStyleModelMapper.mapToEntity(styleToCreate);

        //then
        assertThat(styleEntity).isEqualToComparingFieldByField(expectedStyle);
    }
}