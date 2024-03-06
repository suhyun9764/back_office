package com.sparta.back_office.repository;

import com.sparta.back_office.model.entity.Lecture;
import com.sparta.back_office.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture,Long> {

    List<Lecture> findAllByCategoryOrderByRegisterDateDesc(Category category);
}
