package Main;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**学生类*/
public class Student implements Serializable {
    // 学号（整型）；姓名（字符串），选修课程（名称）及课程成绩（整型）
    private int id;
    private String name;
    Birthday birthday;
    private Map<String, Integer> electiveCourses = new HashMap<>();

    Student(int id, String name, int y, int m, int d){
        this.id = id;
        this.name = name;
        birthday = new Birthday(y, m, d);
    }

    /**输入学生的课程名与相应的成绩*/
    public void inputData(String cla, int score){
        electiveCourses.put(cla, score);
    }

    public String[] show(){
        String []infor = new String[7];
        infor[1] = "id: " + id + "\n";
        infor[2] = "name: " + name + "\n";
        int i = 3;
        for(Map.Entry<String, Integer> map : electiveCourses.entrySet()){
            infor[i] = map.getKey() + ": " + map.getValue() + "\n";
            ++i;
        }
        return infor;
    }

    @Override
    public String toString() {
        return "Student : name = " + name + "; " + "Student : id = " + id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Map<String, Integer> getElectiveCourses() {
        return electiveCourses;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setElectiveCourses(Map<String, Integer> electiveCourses) {
        this.electiveCourses = electiveCourses;
    }
}
