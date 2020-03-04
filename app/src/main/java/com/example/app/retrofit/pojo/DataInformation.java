package com.example.app.retrofit.pojo;

/**
 *                     .::::.
 *                   .::::::::.
 *                  :::::::::::    佛主保佑、永无Bug
 *              ..:::::::::::'
 *            '::::::::::::'
 *              .::::::::::
 *         '::::::::::::::..
 *              ..::::::::::::.
 *            ``::::::::::::::::
 *             ::::``:::::::::'        .:::.
 *            ::::'   ':::::'       .::::::::.
 *          .::::'      ::::     .:::::::'::::.
 *         .:::'       :::::  .:::::::::' ':::::.
 *        .::'        :::::.:::::::::'      ':::::.
 *       .::'         ::::::::::::::'         ``::::.
 *   ...:::           ::::::::::::'              ``::.
 *  ```` ':.          ':::::::::'                  ::::..
 *                     '.:::::'                    ':'````..
 */

/**
 * 第五、第六、第二十五题目数据：二氧化碳、道路状态、光照、PM2.5、湿度、温度的数据
 * 
 * @author xiaoyu
 * @date 2020/3/2 17:10
 */
public class DataInformation {
    private int wendu; // 温度
    private int shidu; // 湿度
    private int guangzhao; // 光照
    private int co; // 二氧化碳
    private int pm; // PM2.5
    private int num1; // 道路一
    private int num2; // 道路二
    private int num3; // 道路三

    public int getWendu() {
        return wendu;
    }

    public void setWendu(int wendu) {
        this.wendu = wendu;
    }

    public int getShidu() {
        return shidu;
    }

    public void setShidu(int shidu) {
        this.shidu = shidu;
    }

    public int getGuangzhao() {
        return guangzhao;
    }

    public void setGuangzhao(int guangzhao) {
        this.guangzhao = guangzhao;
    }

    public int getCo() {
        return co;
    }

    public void setCo(int co) {
        this.co = co;
    }

    public int getPm() {
        return pm;
    }

    public void setPm(int pm) {
        this.pm = pm;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getNum3() {
        return num3;
    }

    public void setNum3(int num3) {
        this.num3 = num3;
    }
}
