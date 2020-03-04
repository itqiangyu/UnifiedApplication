package com.example.app.five_six_twentyfive;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.app.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 道路状态
 */
public class DLZT_Fragment extends Fragment {

    public DLZT_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View tool = inflater.inflate(R.layout.dlzt__fragment, container, false);

        LineChart mChart2 = tool.findViewById(R.id.chart);

        dynamicLineChart = new DLZT_Fragment.DynamicLineChart(mChart2);
        //循环添加数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            list.add(Main.num1);
                            dynamicLineChart.addEntry(list);
                            list.clear();
                        }
                    });
                }
            }
        }).start();
        return tool;
    }

    private DLZT_Fragment.DynamicLineChart dynamicLineChart;
    private List<Integer> list = new ArrayList<>(); //数据集合


    public class DynamicLineChart {

        private LineChart lineChart;
        private YAxis rightAxis;
        private XAxis xAxis;
        private LineData lineData;
        private LineDataSet lineDataSet;
        private List<ILineDataSet> lineDataSets = new ArrayList<>();
        private SimpleDateFormat df = new SimpleDateFormat("mm:ss");    //  设置日期格式  
        private List<String> timeList = new ArrayList<>();  //  存储x轴的时间

        public DynamicLineChart(LineChart mLineChart) {
            this.lineChart = mLineChart;
            lineChart.setDescription(null);     //  图表描述设为无
            rightAxis = lineChart.getAxisRight();   //  获取图表右边的y轴
            rightAxis.setEnabled(false);    //  禁用图表右边的y轴
            xAxis = lineChart.getXAxis();   //  获取折线图x轴

            initLineChart();    //  初始化折线图
            initLineDataSet();  //  初始化折线图数据
        }

        //初始化折现图
        private void initLineChart() {
            lineChart.setDrawBorders(false);    //  不显示边界
            lineChart.setTouchEnabled(false);   //  触摸响应

            // 透明化图例
            Legend legend = lineChart.getLegend();
            legend.setForm(Legend.LegendForm.NONE);
            legend.setTextColor(Color.WHITE);

            //  设置x轴
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // 设置x轴的显示位置,显示在底部
            xAxis.setGranularity(1f);   // 禁止放大后x轴标签重绘
            xAxis.setLabelCount(20);    // 显示x轴的20个数据源
            xAxis.setDrawGridLines(false);   // 设置x轴上每个点对应的线

            xAxis.setValueFormatter(new IAxisValueFormatter() {    //  x轴数值格式化
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return timeList.get((int) value % timeList.size());    //返回时间
                }
            });
        }

        //初始化折线图数据
        private void initLineDataSet() {

            lineDataSet = new LineDataSet(null ,"");    // 向折线图添加数据
            lineDataSet.setLineWidth(2.5f); //  设置折线图线的宽度
            lineDataSet.setColor(Color.parseColor("#AABCC6"));  //  线条颜色
            lineDataSet.setCircleColor(Color.parseColor("#AABCC6"));//  圆点颜色
            //lineDataSet.setCircleRadius(11);
            lineDataSet.setDrawCircleHole(false);   // 设置数据点是空心(false)还是实心(true)，默认空心
            lineDataSet.setCircleColor(Color.parseColor("#AABCC6"));    // 设置数据点的颜色
            lineDataSet.setCircleSize(4);   // 设置数据点的大小

            lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            lineDataSet.setValueTextSize(9f);
            lineDataSets.add(lineDataSet);
            //添加一个空的 LineData
            lineData = new LineData();
            lineChart.setData(lineData);
            lineChart.invalidate();
        }

        //动态添加数据
        public void addEntry(List<Integer> numbers) {

            if (lineDataSets.get(0).getEntryCount() == 0) {
                lineData = new LineData(lineDataSets);
                lineChart.setData(lineData);
            }
            timeList.add(df.format(new Date()));    //  格式化时间为mm:ss并传入时间集合
            for (int i = 0; i < numbers.size(); i++) {
                Entry entry = new Entry(lineDataSet.getEntryCount(), numbers.get(i));
                lineData.addEntry(entry, i);
                lineData.notifyDataChanged();   //  通知lineData刷新
                lineChart.notifyDataSetChanged();   //  通知折线图刷新
                lineChart.setVisibleXRangeMaximum(20);
                lineChart.moveViewToX(lineData.getEntryCount());
            }
        }
    }
}
