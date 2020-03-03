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
    private Integer wendu; // 温度
    private Integer shidu; // 湿度
    private Integer guangzhao; // 光照
    private Integer co; // 二氧化碳
    private Integer pm; // PM2.5
    private Integer num1; // 道路一
    private Integer num2; // 道路二
    private Integer num3; // 道路三

    public Integer getWendu() {
        return wendu;
    }

    public void setWendu(Integer wendu) {
        this.wendu = wendu;
    }

    public Integer getShidu() {
        return shidu;
    }

    public void setShidu(Integer shidu) {
        this.shidu = shidu;
    }

    public Integer getGuangzhao() {
        return guangzhao;
    }

    public void setGuangzhao(Integer guangzhao) {
        this.guangzhao = guangzhao;
    }

    public Integer getCo() {
        return co;
    }

    public void setCo(Integer co) {
        this.co = co;
    }

    public Integer getPm() {
        return pm;
    }

    public void setPm(Integer pm) {
        this.pm = pm;
    }

    public Integer getNum1() {
        return num1;
    }

    public void setNum1(Integer num1) {
        this.num1 = num1;
    }

    public Integer getNum2() {
        return num2;
    }

    public void setNum2(Integer num2) {
        this.num2 = num2;
    }

    public Integer getNum3() {
        return num3;
    }

    public void setNum3(Integer num3) {
        this.num3 = num3;
    }
}
