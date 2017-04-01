package com.psi.androidhttpclient.netWork;

/**
 * Created by Administrator on 2017-03-07.
 */
public class Person {
  private String name;
  private String score;
  private  int  age;

  public String getName() {
    return name;
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
    return "Person{" +
        "name='" + name + '\'' +
        ", score='" + score + '\'' +
        ", age=" + age +
        '}';
  }

  public Person(String name, String score, int age) {
    this.name = name;
    this.score = score;
    this.age = age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
