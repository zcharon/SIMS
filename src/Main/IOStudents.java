package Main;

import java.util.*;
import java.io.*;

public class IOStudents {
    public static void main(String[] args) {
        Scanner in = new Scanner((System.in));
        // 文件存取路径
        String path = "data1.dat";

        System.out.println("请输入进行的操作（1、存学生；2、读文件）");
        int tag = in.nextInt();

        try {
            if(tag == 1){
                writeData(path);
            }
            else if(tag == 2){
                readData(path);
            }
        }
        catch (ClassNotFoundException | IOException ioe){
            ioe.printStackTrace();
        } finally {
            in.close();
        }
    }

    public static void readData(String path) throws IOException, ClassNotFoundException {
        System.out.println("读取到的学生为：");

        File file = new File(path);
        ObjectInputStream read = new ObjectInputStream(new FileInputStream(file));

        Object obj = null;
        int numOfStu = 0;
        StuDatas stuDatas = new StuDatas();
        while ((obj = read.readObject()) != null){
            Student student = (Student) obj;
            stuDatas.datas.add(student);
            System.out.println(student);
            ++numOfStu;
        }
        read.close();
        System.out.println("学生读取完成，共" + numOfStu + "人。");

        Map<String, Double> danStatistics = stuDatas.DanStatisticsOnClass("chinese");
        BarChart drawBar = new BarChart("chinese", danStatistics);
        drawBar.draw();
        PieChart pieChart = new PieChart("chinese", danStatistics);
        pieChart.draw();
    }

    public static List<Student> readData(String path, int i) throws IOException, ClassNotFoundException {
        System.out.println("读取成功！！");

        File file = new File(path);
        ObjectInputStream read = new ObjectInputStream(new FileInputStream(file));

        Object obj = null;
//        int numOfStu = 0;
//        StuDatas stuDatas = new StuDatas();
//        while ((obj = read.readObject()) != null){
//            Student student = (Student) obj;
//            stuDatas.datas.add(student);
//            System.out.println(student);
//            ++numOfStu;
//        }
        obj = read.readObject();
        read.close();
        return ((List<Student>) obj);
//        System.out.println("学生读取完成，共" + numOfStu + "人。");
//
//        Map<String, Double> danStatistics = stuDatas.DanStatisticsOnClass("chinese");
//        BarChart drawBar = new BarChart("chinese", danStatistics);
//        drawBar.draw();
//        PieChart pieChart = new PieChart("chinese", danStatistics);
//        pieChart.draw();
    }

    public static void writeData(String path) throws IOException {
        Scanner in = new Scanner((System.in));
        System.out.println("请输入您想存入学生的数量：");
        int numOfStudent = in.nextInt();
        System.out.println("请输入每位学生的科目数：");
        int numOfClass = in.nextInt();
        File file = new File(path);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        in.nextLine();
        for (int i = 0; i < numOfStudent; ++i) {
            System.out.println("请输入第" + i + 1 + "位学生的学号、姓名、生日(yy mm dd) 用空格分隔");
            String str = in.nextLine();
            String[] strs = str.split(" ");
            Student student = new Student(Integer.parseInt(strs[0]), strs[1], Integer.parseInt(strs[2]), Integer.parseInt(strs[3]),
                    Integer.parseInt(strs[4]));

            System.out.println("请输入该学生" + numOfClass + "门课的课程与成绩：");
            for(int j = 0; j < numOfClass; ++j){
                String cla = in.next();
                int score = in.nextInt();
                student.inputData(cla, score);
            }
            out.writeObject((Object) student);
            in.nextLine();
        }
        out.writeObject(null);
        out.flush();
        System.out.println("文件写入成功！！");
        // in.close();
    }

    public static void writeData(String path, List<Student> students) throws IOException {
        // Scanner in = new Scanner((System.in));
        // System.out.println("请输入您想存入学生的数量：");
        // int numOfStudent = in.nextInt();
        // System.out.println("请输入每位学生的科目数：");
        // int numOfClass = in.nextInt();
        File file = new File(path);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        // in.nextLine();
//        for (int i = 0; i < numOfStudent; ++i) {
//            System.out.println("请输入第" + i + 1 + "位学生的学号、姓名、生日(yy mm dd) 用空格分隔");
//            String str = in.nextLine();
//            String[] strs = str.split(" ");
//            Student student = new Student(Integer.parseInt(strs[0]), strs[1], Integer.parseInt(strs[2]), Integer.parseInt(strs[3]),
//                    Integer.parseInt(strs[4]));
//
//            System.out.println("请输入该学生" + numOfClass + "门课的课程与成绩：");
//            for(int j = 0; j < numOfClass; ++j){
//                String cla = in.next();
//                int score = in.nextInt();
//                student.inputData(cla, score);
//            }
//            out.writeObject((Object) student);
//            in.nextLine();
//        }
//        String[] strs = birthday.split("/");
//        Student student = new Student(Integer.parseInt(strs[0]), strs[1], Integer.parseInt(strs[2]), Integer.parseInt(strs[3]),
//                Integer.parseInt(strs[4]));
//        // Math/Cinese/English/Computrt
//        String []cla = {"Math", "Cinese", "English", "Computrt"};
//        String []stuScore = score.split("/");
//        for(int i = 0; i < 4; ++i){
//            student.inputData(cla[i], Integer.parseInt(stuScore[i]));
//        }
        out.writeObject(students);
        out.writeObject(null);
        out.flush();
        System.out.println("文件写入成功！！");
        // in.close();
    }
}

/*
1
5
4
1 zhou 1999 9 18
match 100 chinese 100 english 100 computer 100
2 shi 1999 10 10
match 105 chinese 80 english 130 computer 70
3 cao 2000 6 2
match 100 chinese 110 english 70 computer 60
4 chang 2001 4 5
match 70 chinese 75 english 80 computer 90
5 li 1998 4 4
match 130 chinese 80 english 100 computer 95
*/
