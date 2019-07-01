package io.admin.common.utils;

public class SerialNumUtils {

    /**
     * 获得下一个流水号
     * @param array
     * @param len
     * @return
     */
    public static String createSerialNum(String[] array, int len){
        int maxNum = 1; //初始化最大值为1
        String SerialNum;
        if(array.length > 0){
            for(int i = 0; i < array.length ; i++){
                String num = array[i];
                String previousSerialNum = num.substring(num.length() - len); //获得培养方案号号后3位
                int newNum = Integer.parseInt(previousSerialNum.replaceAll("^(0+)", ""));// 去掉前面的0
                if(newNum > maxNum){
                    maxNum = newNum;
                }
            }
            SerialNum = String.format("%0" + len + "d", (maxNum + 1)); // 数值前面加0，保存为len位
            return SerialNum;
        }else{
            SerialNum = String.format("%0" + len + "d",  1);
            return  SerialNum;
        }
    }

}
