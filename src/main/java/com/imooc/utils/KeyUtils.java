package com.imooc.utils;

import java.util.Random;

/**
 * Created by msi on 2019/7/14.
 */
public class KeyUtils {

    /**
     * 生成唯一组件
     * @return 时间+谁机数
     * 锁对象是当前字节码
     */
    public  static synchronized   String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(90000) + 10000;
        return    System.currentTimeMillis()+
                String.valueOf(number);
    }

}
