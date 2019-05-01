package com.mentoring.discolibrary.style;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface StyleRepository extends JpaRepository<StyleEntity, Integer> {

    List<Style> findAllByOrderByNameAsc();

}
