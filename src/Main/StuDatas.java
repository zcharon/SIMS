package Main;

import java.util.*;

public class StuDatas {
    List<Student>  datas=  new LinkedList<>();
    StuDatas(){}

    /**获得某个ID的平均成绩*/
    public double getAverageScores(int id){
        double sumScore = 0;
        int count = 0;
        for(Student stu : datas){
            // 遍历该ID的所有成绩，求和
            if(stu.getId() == id){
                for(Map.Entry<String, Integer> score: stu.getElectiveCourses().entrySet()){
                    sumScore += score.getValue();
                    ++count;
                }
                break;
            }
        }
        // 返回平均值
        return sumScore / count;
    }

    /**获得某个课程的平均成绩*/
    public double getAverageScores(String cla){
        double sumScore = 0;
        int count = 0;
        for(Student stu : datas){
            // 只加该课程的成绩
            sumScore += stu.getElectiveCourses().get(cla);
            ++count;
        }
        return sumScore / count;
    }

    /**得到某个ID的所有课程及成绩*/
    public Map<String, Integer> getScore(int id){
        for(Student stu : datas){
            if(stu.getId() == id){
                return stu.getElectiveCourses();
            }
        }
        return null;
    }

    /**根据每位学生的平均成绩进行排序*/
    public void sort(){
        class sortOnScore implements Comparator<Student>{
            @Override
            public int compare(Student o1, Student o2) {
               double score1 = 0;
               double score2 = 0;
               int count = 0;
               for(Map.Entry<String, Integer> score : o1.getElectiveCourses().entrySet()){
                   score1 += score.getValue();
                   ++count;
               }

                for(Map.Entry<String, Integer> score : o2.getElectiveCourses().entrySet()){
                    score2 += score.getValue();
                }

                return (int)(score1 / count - score2 /  count);
            }
        }
        datas.sort(new sortOnScore());
    }

    /**得到每个段位的人数，默认-> 0：优秀；1：良好；2: 中等；3：及格；4：不及格*/
    private  int[] danStatistics(String cla){
       // 优秀90、良好80、中等70、及格60、不及格
       int []dans = new int[5];  // 记录各段位的人数
       for(int i = 0; i < 5; ++i){dans[i] = 0;}  // 初始化
       for(Student stu : datas){
           for(Map.Entry<String, Integer> set : stu.getElectiveCourses().entrySet()){
               if(cla.equals(set.getKey())){  // 如果当前课程位要统计的课程
                   // 课程成绩分段
                   if(set.getValue() >= 90){
                       dans[0]++;
                   }
                   else if(set.getValue() >= 80){
                       dans[1]++;
                   }
                   else if(set.getValue() >= 70){
                       dans[2]++;
                   }
                   else if(set.getValue() >= 60){
                       dans[3]++;
                   }
                   else{
                       dans[4]++;
                   }
               }
           }
       }
       return dans;
    }

    public Map<String, Double> DanStatisticsOnClass(String cla){
        String []strs = {"优秀", "良好", "中等", "及格", "不及格"};
        int []dansStu = danStatistics(cla);  // 每个段位的人数
        double[] danScale = new double[dansStu.length];  // 每个段位的比例
        // 计算每个段位的比例
        for(int i = 0; i < strs.length; ++i){
            danScale[i] = (double) dansStu[i] / strs.length;
        }

        Map<String, Double> passRateOncla = new LinkedHashMap<>();

        // 将课程与所占的比例存入Map中
        for(int i = 0; i < strs.length; ++i){
            passRateOncla.put(strs[i], danScale[i]);
        }
        return passRateOncla;
    }
}

