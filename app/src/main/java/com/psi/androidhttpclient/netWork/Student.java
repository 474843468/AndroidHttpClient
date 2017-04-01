package com.psi.androidhttpclient.netWork;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-03-07.
 */
public class Student{
  private String name;
  private String score;
  private  int  age;
  private ArrayList<Person> list;

  public ArrayList<Person> getList() {
    return list;
  }

  public void setList(ArrayList<Person> list) {
    this.list = list;
  }

  public String getName() {
    return name;
  }

  public Student(String name, String score, int age) {
    this.name = name;
    this.score = score;
    this.age = age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }

  public int getAge() {
    return age;
  }

  @Override public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", score='" + score + '\'' +
        ", age=" + age +
        ", list=" + list +
        '}';
  }

  public void setAge(int age) {
    this.age = age;
  }
}
