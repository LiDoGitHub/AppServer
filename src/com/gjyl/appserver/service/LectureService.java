package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.Lecture;

import java.util.List;

public interface LectureService {

	List<Lecture> getAllLectures();

    Boolean addLecture(Lecture lecture);

    Lecture getLectureById(String lecid);

    Boolean updateLecture(Lecture lecture);

    Boolean deleteLecById(String lecid);
}
