package com.mentoring.discolibrary.style;

import org.springframework.data.jpa.repository.JpaRepository;

interface StyleRepository extends JpaRepository<StyleEntity, Integer> {
    boolean styleExists(String styleName);
}
