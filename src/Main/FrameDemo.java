package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FrameDemo{
    private static List<Student> studentList = new LinkedList<>();
    private static IOStudents IOStu = new IOStudents();
    private static String file_path = "./data.dat";
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        //设置标题
        frame.setTitle("学生信息管理系统");
        frame.setBounds(400,200,400,700);

        frame.setLayout(new GridLayout(2,2,20,20));
        Button addButton = new Button("Add Students information");
        Button deleteButton = new Button("Delete students information");
        Button findButton = new Button("Find students information");
        Button modifyButton = new Button("Modify students information");

        //add buttons
        frame.add(addButton,new GridLayout(1,1));
        frame.add(deleteButton);
        frame.add(findButton);
        frame.add(modifyButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                find();;
            }
        });
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //默认窗体不显示，要设置
        frame.setVisible(true);
    }

    private static void add() {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new GridLayout(6,1));
        jFrame.setBounds(400, 200, 430, 700);
        String []stuName = new String[2];
        String []stuId = new String[2];
        String []stuBirth = new String[2];
        String []stuScore = new String[2];

        /////////////////////////////////////////////////////////////////////////////
        // 设置标题
        jFrame.setTitle("学生信息管理系统：录入界面\n");
        //创建面板并放在窗体上半部分
        JPanel panelId = new JPanel();
        jFrame.add(panelId, new GridLayout(2, 3));

        //创建提示标签并显示提示信息
        JLabel show = new JLabel();
        show.setText("请输入学生ID");

        //创建输入框，设置为可编辑，左侧输入，20列
        JTextField inputId = new JTextField();
        inputId.setEditable(true);
        // inputId.setHorizontalAlignment(SwingConstants.LEFT);
        inputId.setColumns(20);
        //创建按钮组件
        JButton jButton = new JButton("提交");
        //添加显示标签、输入框和按钮到容器中
        panelId.add(show, new GridLayout(1, 1));
        panelId.add(inputId);
        panelId.add(jButton);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuId[0] = inputId.getText().toString();

            }
        });

        /////////////////////////////////////////////////////////////////////////////
        //创建面板并放在窗体上半部分
        JPanel panelName = new JPanel();
        jFrame.add(panelName, new GridLayout(2, 3));

        //创建提示标签并显示提示信息
        JLabel showName = new JLabel();
        showName.setText("请输入学生姓名：");

        //创建输入框，设置为可编辑，左侧输入，20列
        JTextField inputName = new JTextField();
        inputName.setEditable(true);
        // inputId.setHorizontalAlignment(SwingConstants.LEFT);
        inputName.setColumns(20);
        //创建按钮组件
        JButton jButton2 = new JButton("提交");
        //添加显示标签、输入框和按钮到容器中
        panelName.add(showName, new GridLayout(2, 1));
        panelName.add(inputName);
        panelName.add(jButton2);

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuName[0] = inputName.getText().toString();
            }
        });

        /////////////////////////////////////////////////////////////////////////////
        //创建面板并放在窗体上半部分
        JPanel panelBirthday = new JPanel();
        jFrame.add(panelBirthday, new GridLayout(2, 3));

        //创建提示标签并显示提示信息
        JLabel showBith = new JLabel();
        showBith.setText("请输入学生生日：");

        //创建输入框，设置为可编辑，左侧输入，20列
        String birthdayStr = "YY/MM/DD";
        JTextField inputBirth = new JTextField(birthdayStr);
        inputName.setEditable(true);
        // inputId.setHorizontalAlignment(SwingConstants.LEFT);
        inputBirth.setColumns(20);
        //创建按钮组件
        JButton jButton3 = new JButton("提交");
        //添加显示标签、输入框和按钮到容器中
        panelBirthday.add(showBith, new GridLayout(3, 1));
        panelBirthday.add(inputBirth);
        panelBirthday.add(jButton3);

        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuBirth[0] = inputBirth.getText().toString();
            }
        });

        /////////////////////////////////////////////////////////////////////////////
        //创建面板并放在窗体上半部分
        JPanel panelClas = new JPanel();
        jFrame.add(panelClas, new GridLayout(4, 1));

        //创建提示标签并显示提示信息
        JLabel showClas = new JLabel();
        showClas.setText("请输入学生分数：");

        //创建输入框，设置为可编辑，左侧输入，20列
        String colStr = "Math/Chinese/English/Computer";
        JTextField inputScore = new JTextField(colStr);
        inputName.setEditable(true);
        // inputId.setHorizontalAlignment(SwingConstants.LEFT);
        inputScore.setColumns(20);
        //创建按钮组件
        JButton jButton4 = new JButton("提交");
        //添加显示标签、输入框和按钮到容器中
        panelClas.add(showClas, new GridLayout(3, 1));
        panelClas.add(inputScore);
        panelClas.add(jButton4);

        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuScore[0] = inputScore.getText().toString();
                String []birthday = stuBirth[0].split("/");
                Student student = new Student(Integer.parseInt(stuId[0]), stuName[0], Integer.parseInt(birthday[0]),
                        Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]));
                String []cla = {"Math", "Chinese", "English", "Computer"};
                String []score = stuScore[0].split("/");
                for(int i = 0; i < 4; ++i){
                    student.inputData(cla[i], Integer.parseInt(score[i]));
                }
                studentList.add(student);
                try {
                    IOStudents.writeData(file_path, studentList);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });


        /////////////////////////////////////////////////////////////////////////////
        //创建面板并放在窗体上半部分
        JPanel bottomPanel = new JPanel();
        jFrame.add(bottomPanel,BorderLayout.CENTER);

        //创建提示标签并设置显示信息
        JLabel againshow = new JLabel();
        againshow.setText("显示所有保存学生信息：");

        //创建输出框,5行32列，不可编辑
        JTextArea output = new JTextArea();
        output.setRows(45);
        output.setColumns(35);
        output.setEditable(false);

        JButton jButton6 = new JButton("更新信息");

        //添加标签和输出框至下部的面板
        bottomPanel.add(jButton6);
        bottomPanel.add(againshow);
        bottomPanel.add(output);

        jButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentList.size() <= 0){
                    try {
                        studentList = IOStudents.readData(file_path, 1);
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                output.setText("");
                for(Student stu : studentList){
                    output.append(stu.toString() + "\n");
                }
            }
        });

        //设置窗体关闭方式,默认值是3
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //默认窗体不显示，要设置
        jFrame.setVisible(true);
    }

    private static void delete() {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new GridLayout(6,1));
        jFrame.setBounds(400, 200, 430, 700);

        /////////////////////////////////////////////////////////////////////////////
        // 设置标题
        jFrame.setTitle("学生信息管理系统：删除界面\n");
        //创建面板并放在窗体上半部分
        JPanel panelId = new JPanel();
        jFrame.add(panelId, new GridLayout(2, 3));

        //创建提示标签并显示提示信息
        JLabel show = new JLabel();
        show.setText("请输入删除学生的ID");

        //创建输入框，设置为可编辑，左侧输入，20列
        JTextField inputId = new JTextField();
        inputId.setEditable(true);
        // inputId.setHorizontalAlignment(SwingConstants.LEFT);
        inputId.setColumns(20);
        String stuId = inputId.getText();
        //创建按钮组件
        JButton jButton = new JButton("提交");
        //添加显示标签、输入框和按钮到容器中
        panelId.add(show, new GridLayout(1, 1));
        panelId.add(inputId);
        panelId.add(jButton);

        /////////////////////////////////////////////////////////////////////////////
        //创建面板并放在窗体上半部分
        JPanel bottomPanel = new JPanel();
        jFrame.add(bottomPanel,BorderLayout.CENTER);

        //创建提示标签并设置显示信息
        JLabel againshow = new JLabel();
        againshow.setText("显示当前存在学生的信息：");

        //创建输出框,5行32列，不可编辑
        JTextArea output = new JTextArea();
        output.setRows(15);
        output.setColumns(35);
        output.setEditable(false);
        //添加标签和输出框至下部的面板
        bottomPanel.add(againshow);
        bottomPanel.add(output);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentList.size() <= 0){
                    try {
                        studentList = IOStudents.readData(file_path,1);
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                int id = Integer.parseInt(inputId.getText());
                boolean tag = true;
                for(int i = 0; i < studentList.size(); ++i){
                    if(studentList.get(i).getId() == id){
                        studentList.remove(i);
                        tag = false;
                        output.setText("删除完毕！！！");
                        try {
                            IOStudents.writeData(file_path, studentList);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    }
                }
                if(tag){
                    output.setText("查无此人！！！");
                }
            }
        });
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //默认窗体不显示，要设置
        jFrame.setVisible(true);
        // System.out.println("删除功能");
    }

    private static void find() {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new GridLayout(6,1));
        jFrame.setBounds(400, 200, 430, 700);

        /////////////////////////////////////////////////////////////////////////////
        // 设置标题
        jFrame.setTitle("学生信息管理系统：查找界面\n");
        //创建面板并放在窗体上半部分
        JPanel panelId = new JPanel();
        jFrame.add(panelId, new GridLayout(2, 3));

        //创建提示标签并显示提示信息
        JLabel show = new JLabel();
        show.setText("请输入查找学生的ID");

        //创建输入框，设置为可编辑，左侧输入，20列
        JTextField inputId = new JTextField();
        inputId.setEditable(true);
        // inputId.setHorizontalAlignment(SwingConstants.LEFT);
        inputId.setColumns(20);
        String stuId = inputId.getText();
        //创建按钮组件
        JButton jButton = new JButton("提交");
        //添加显示标签、输入框和按钮到容器中
        panelId.add(show, new GridLayout(1, 1));
        panelId.add(inputId);
        panelId.add(jButton);

        /////////////////////////////////////////////////////////////////////////////
        //创建面板并放在窗体上半部分
        JPanel panelName = new JPanel();
        jFrame.add(panelName, new GridLayout(2, 3));

        //创建提示标签并显示提示信息
        JLabel showName = new JLabel();
        showName.setText("请输入需绘制统计图的科目：");

        //创建输入框，设置为可编辑，左侧输入，20列
        JTextField inputCla = new JTextField("Math/Chinese/English/Computer");
        inputCla.setEditable(true);
        // inputId.setHorizontalAlignment(SwingConstants.LEFT);
        inputCla.setColumns(20);
        //创建按钮组件
        JButton jButton2 = new JButton("提交");
        //添加显示标签、输入框和按钮到容器中
        panelName.add(showName, new GridLayout(2, 1));
        panelName.add(inputCla);
        panelName.add(jButton2);

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentList.size() <= 0){
                    try {
                        studentList = IOStudents.readData(file_path, 1);
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                String cla = inputCla.getText().toString();
                StuDatas stuDatas = new StuDatas();
                stuDatas.datas.addAll(studentList);
                Map<String, Double> danStatistics = stuDatas.DanStatisticsOnClass(cla);
                BarChart drawBar = new BarChart(cla, danStatistics);
                drawBar.draw();
                PieChart pieChart = new PieChart(cla, danStatistics);
                pieChart.draw();
            }
        });
        /////////////////////////////////////////////////////////////////////////////
        //创建面板并放在窗体上半部分
        JPanel bottomPanel = new JPanel();
        jFrame.add(bottomPanel,BorderLayout.CENTER);

        //创建提示标签并设置显示信息
        JLabel againshow = new JLabel();
        againshow.setText("当前学生的基本信息为：");

        //创建输出框,5行32列，不可编辑
        JTextArea output = new JTextArea();
        output.setRows(35);
        output.setColumns(35);
        output.setEditable(false);
        //添加标签和输出框至下部的面板
        bottomPanel.add(againshow);
        bottomPanel.add(output);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText("");
                if(studentList.size() <= 0){
                    try {
                        studentList = IOStudents.readData(file_path,1);
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                int id = Integer.parseInt(inputId.getText());
                boolean tag = true;
                for (Student student : studentList) {
                    if (student.getId() == id) {
                        tag = false;
                        String[] infor = student.show();
                        for (String s : infor) {
                            output.append(s);
                        }
                        break;
                    }
                }
                if(tag){
                    output.setText("查无此人！！！");
                }
            }
        });
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //默认窗体不显示，要设置
        jFrame.setVisible(true);
        // System.out.println("删除功能");
        // System.out.println("修改功能");
    }

    private static void update() {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new GridLayout(6,1));
        jFrame.setBounds(400, 200, 430, 700);
        String []stuName = new String[2];
        String []stuId = new String[2];
        String []stuBirth = new String[2];
        String []stuScore = new String[2];
        final boolean[] tag = {false};

        /////////////////////////////////////////////////////////////////////////////
        // 设置标题
        jFrame.setTitle("学生信息管理系统：修改界面\n");
        //创建面板并放在窗体上半部分
        JPanel panelId = new JPanel();
        jFrame.add(panelId, new GridLayout(2, 3));

        //创建提示标签并显示提示信息
        JLabel show = new JLabel();
        show.setText("待修改学生ID");

        //创建输入框，设置为可编辑，左侧输入，20列
        JTextField inputId = new JTextField();
        inputId.setEditable(true);
        // inputId.setHorizontalAlignment(SwingConstants.LEFT);
        inputId.setColumns(20);
        //创建按钮组件
        JButton jButton = new JButton("提交");
        //添加显示标签、输入框和按钮到容器中
        panelId.add(show, new GridLayout(1, 1));
        panelId.add(inputId);
        panelId.add(jButton);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuId[0] = inputId.getText().toString();
            }
        });
        /////////////////////////////////////////////////////////////////////////////
        //创建面板并放在窗体上半部分
        JPanel panelName = new JPanel();
        jFrame.add(panelName, new GridLayout(2, 3));

        //创建提示标签并显示提示信息
        JLabel showName = new JLabel();
        showName.setText("修改后学生姓名：");

        //创建输入框，设置为可编辑，左侧输入，20列
        JTextField inputName = new JTextField();
        inputName.setEditable(true);
        // inputId.setHorizontalAlignment(SwingConstants.LEFT);
        inputName.setColumns(20);
        //创建按钮组件
        JButton jButton2 = new JButton("提交");
        //添加显示标签、输入框和按钮到容器中
        panelName.add(showName, new GridLayout(2, 1));
        panelName.add(inputName);
        panelName.add(jButton2);

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuName[0] = inputName.getText().toString();
            }
        });
        /////////////////////////////////////////////////////////////////////////////
        //创建面板并放在窗体上半部分
        JPanel panelBirthday = new JPanel();
        jFrame.add(panelBirthday, new GridLayout(2, 3));

        //创建提示标签并显示提示信息
        JLabel showBith = new JLabel();
        showBith.setText("修改后学生生日：");

        //创建输入框，设置为可编辑，左侧输入，20列
        String birthdayStr = "YY/MM/DD";
        JTextField inputBirth = new JTextField(birthdayStr);
        inputName.setEditable(true);
        // inputId.setHorizontalAlignment(SwingConstants.LEFT);
        inputBirth.setColumns(20);
        //创建按钮组件
        JButton jButton3 = new JButton("提交");
        //添加显示标签、输入框和按钮到容器中
        panelBirthday.add(showBith, new GridLayout(3, 1));
        panelBirthday.add(inputBirth);
        panelBirthday.add(jButton3);

        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuBirth[0] = inputBirth.getText().toString();
            }
        });
        /////////////////////////////////////////////////////////////////////////////
        //创建面板并放在窗体上半部分
        JPanel panelClas = new JPanel();
        jFrame.add(panelClas, new GridLayout(4, 1));

        //创建提示标签并显示提示信息
        JLabel showClas = new JLabel();
        showClas.setText("修改后学生分数：");

        //创建输入框，设置为可编辑，左侧输入，20列
        String colStr = "Math/Cinese/English/Computrt";
        JTextField inputScore = new JTextField(colStr);
        inputName.setEditable(true);
        // inputId.setHorizontalAlignment(SwingConstants.LEFT);
        inputScore.setColumns(20);
        //创建按钮组件
        JButton jButton4 = new JButton("提交");
        //添加显示标签、输入框和按钮到容器中
        panelClas.add(showClas, new GridLayout(3, 1));
        panelClas.add(inputScore);
        panelClas.add(jButton4);

        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuScore[0] = inputScore.getText().toString();
                String[] birthday = stuBirth[0].split("/");
                for (int i = 0; i < studentList.size(); ++i) {
                    if (studentList.get(i).getId() == Integer.parseInt(stuId[0])) {
                        studentList.remove(i);
                        tag[0] = true;
                    }
                }
                if (tag[0]) {
                    Student student = new Student(Integer.parseInt(stuId[0]), stuName[0], Integer.parseInt(birthday[0]),
                            Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]));
                    String[] cla = {"Math", "Chinese", "English", "Computer"};
                    String[] score = stuScore[0].split("/");
                    for (int i = 0; i < 4; ++i) {
                        student.inputData(cla[i], Integer.parseInt(score[i]));
                    }
                    studentList.add(student);
                    try {
                        IOStudents.writeData(file_path, studentList);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        /////////////////////////////////////////////////////////////////////////////
        JButton jButton6 = new JButton("更新信息");
        //创建面板并放在窗体上半部分
        JPanel bottomPanel = new JPanel();
        jFrame.add(bottomPanel,BorderLayout.CENTER);
        bottomPanel.add(jButton6);

        //创建提示标签并设置显示信息
        JLabel againshow = new JLabel();
        againshow.setText("修改后学生的信息为：");

        //创建输出框,5行32列，不可编辑
        JTextArea output = new JTextArea();
        output.setRows(35);
        output.setColumns(35);
        output.setEditable(false);
        //添加标签和输出框至下部的面板
        bottomPanel.add(againshow);
        bottomPanel.add(output);
        jButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentList.size() <= 0){
                    try {
                        studentList = IOStudents.readData(file_path, 1);
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                if(!tag[0]){
                    output.setText("查无此人！！！");
                }
                else{
                output.setText("");
                for(Student stu : studentList){
                    if(stu.getId() == Integer.parseInt(stuId[0])){
                        String []info = stu.show();
                        for(String str : info){
                            output.append(str);
                        }
                        break;
                    }
                }
            }
        }
        });
        //设置窗体关闭方式,默认值是3
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //默认窗体不显示，要设置
        jFrame.setVisible(true);
        // System.out.println("查找功能");
    }
}
