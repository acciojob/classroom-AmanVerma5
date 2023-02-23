package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    private HashMap<String,Student> studentMap;
    private HashMap<String,Teacher> teacherMap;
    private HashMap<String,List<String>> teacherMapsStudent;

    public StudentRepository(){
        studentMap=new HashMap<>();
        teacherMap=new HashMap<>();
        teacherMapsStudent=new HashMap<>();
    }
    public void addStudent(Student student) {
        studentMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(studentMap.containsKey(student)&& studentMap.containsKey(teacher)){
            List<String> currentStudent=new ArrayList<>();
            if(teacherMapsStudent.containsKey(teacher)) currentStudent=teacherMapsStudent.get(teacher);
            currentStudent.add(student);
            teacherMapsStudent.put(teacher,currentStudent);
        }
    }

    public Student getStudentByName(String student) {
        return studentMap.get(student);
    }

    public Teacher getTeacherByName(String teacher) {
        return teacherMap.get(teacher);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return teacherMapsStudent.get(teacher);
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacherByName(String teacher) {
        teacherMap.remove(teacher);
        List<String> students=new ArrayList<>();
        if(teacherMapsStudent.containsKey(teacher)){
            students=teacherMapsStudent.get(teacher);
            for(String student:students){
                if(studentMap.containsKey(student)) studentMap.remove(student);
            }

            teacherMapsStudent.remove(teacher);
        }

    }

    public void deleteAllTeachers() {

        teacherMap.clear();
        List<String> students=new ArrayList<>();
        for(String teacher:teacherMapsStudent.keySet()){
            students=teacherMapsStudent.get(teacher);
            for(String student:students){
                if(studentMap.containsKey(student)) studentMap.remove(student);
            }
        }
        teacherMapsStudent.clear();
    }
}
