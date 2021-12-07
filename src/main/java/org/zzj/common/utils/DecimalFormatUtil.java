package org.zzj.common.utils;

import java.text.DecimalFormat;

/**
 * @author huangfl
 * @date 2018/10/23
 * @Version 1.0
 **/
public class DecimalFormatUtil {


    /**
     * 精度格式化
     * @param numValue  对象
     * @param scale  保留精度
     * @return
     */
    public static String format(Object numValue,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        if(numValue==null)return "";

        String pattern="###########";
        for (int i = 1; i <= scale; i++) {
            if(i==1){
                pattern += "0.0";
            }else {
                pattern += "0";
            }
        }
        DecimalFormat fnum  =   new DecimalFormat(pattern);
        return fnum.format(numValue);
    }

    public static void main(String[] args) {

        System.out.println(DecimalFormatUtil.format(222123.3111,3));
    }
}
