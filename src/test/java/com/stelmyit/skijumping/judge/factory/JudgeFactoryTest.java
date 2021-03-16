package com.stelmyit.skijumping.judge.factory;

import com.stelmyit.skijumping.judge.dto.JudgeDTO;
import com.stelmyit.skijumping.judge.model.Judge;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JudgeFactoryTest {

    private final JudgeFactory uut = new JudgeFactory();

    private static final String FIRST_NAME = "Jacek";
    private static final String LAST_NAME = "Placek";

    @Test
    public void shouldCreateDto() {
        // given
        final Judge judge = Judge.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();

        // when
        final JudgeDTO dto = uut.createDTO(judge);

        // then
        assertThat(dto.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(dto.getLastName()).isEqualTo(LAST_NAME);
    }
}
