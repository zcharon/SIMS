package Main;//这是引入包名，根据自己创建的包名可以进行修改
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;

import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.GridLayout;
import java.util.Map;
import javax.swing.JFrame;

public class PieChart {
    ChartPanel frame1;
    String title;  // 规定图标的主体
    Map<String, Double> map;  // 规定绘画的图

    public  PieChart(String title, Map<String, Double> map){
        this.title = title;
        this.map = map;

        DefaultPieDataset dataset = getDataSet();//将获得的数据传递给CategoryDataset类的对象
        JFreeChart chart = ChartFactory.createPieChart(
                "考试等级比例表",  //图表标题
                dataset,       //数据
                true,               //是否显示图例
                false,              //是否显示工具提示
                false               //是否生成URL
        );

        //取得统计图表的第一个图例
        LegendTitle legend = chart.getLegend(0);
        //修改图例的字体
        legend.setItemFont(new Font("宋体", Font.BOLD, 14));
        //获得饼图的Plot对象
        PiePlot plot = (PiePlot)chart.getPlot();
        //设置饼图各部分的标签字体
        plot.setLabelFont(new Font("隶属", Font.BOLD, 18));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20)); //设置标题字体

        frame1 = new ChartPanel(chart,true);  //这里也可以用chartFrame,可以直接生成一个独立的Frame
    }

    /**提供绘制柱状图所需数据*/
    private  DefaultPieDataset getDataSet() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        // 柱形图内添加元素
        for(Map.Entry<String, Double> mapset : map.entrySet()){
            dataset.setValue(mapset.getKey(), mapset.getValue());
        }
        return dataset;
    }

    /**绘图函数*/
    public  void draw(){
        JFrame frame = new JFrame(title);
        frame.setLayout(new GridLayout(1,1,10,10));
        frame.add(frame1);   //添加柱形图
        frame.setBounds(0, 0, 500, 500);
        frame.setVisible(true);
    }
}
