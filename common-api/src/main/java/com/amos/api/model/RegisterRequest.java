package com.amos.api.model;

import java.io.Serializable;
import java.util.Map;

/**
 * RegisterReq
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/5/29
 */
public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = 2662735364592867769L;

    private String name;
    private String gender;
    private Integer age;
    private Map<String, String> extInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", extInfo=" + extInfo +
                '}';
    }
}
