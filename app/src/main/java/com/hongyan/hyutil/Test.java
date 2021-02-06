package com.hongyan.hyutil;

import java.util.Arrays;

/**
 * @created: by Android Studio.
 * @author: hongyan.tao
 * @date: 2020/11/13
 * @describe: Test
 */
public class Test {
    private  int count = 0;
    public  void main(String[] args) {
        int a[] = new int[100]; //100盏灯的状态
        Arrays.fill(a, 0);
        for (int i = 1; i <= a.length; i++){    //100个人
            for (int j = 0; j < a.length; j++){    //100盏灯
                if ((j+1) % i == 0){ //第（j+1）盏灯对于被第i个人的行为后的状态。
                    if (a[j] == 0){    //原来的状态取反
                        a[j] = 1;
                    }else{
                        a[j] = 0;
                    }
                }
            }
        }
        for (int c : a){
            if (c == 1){
                count ++;
            }
        }
        System.out.println(count);
    }
}
