package com.example.foolishfan.user_v10;

import java.io.Serializable;

/**
 * 人
 */
public class Person implements Serializable {
    /**
     * 序列化的版本号
     */
    private static final long serialVersionUID = 1L;
    /**
     * 组件
     */
    private int _id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private int age;
    /**
     * 性别
     */
    private String sex;
    /**
     * set方法
     * get方法
     */
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
