package Main;

import java.io.Serializable;

/**生日类*/
public class Birthday implements Serializable {
    int year;
    int month;
    int day;

    public Birthday(int y, int m, int d){
        year = y;
        month = m;
        day = d;
    }

    @Override
    public String toString() {
        return "birthday : " + year + " / " + month + " / " + day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
