package com.secretk.move;

/**
 * 作者： litongge
 * 时间：  2018/8/2 18:53
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class JavaText {
    public static void main(String[] args) {

        
        double num = 0.0000001000343000;
        if(num>=1000){
            System.out.print("String.format(\"%.2f\", num)："+String.format("%.2f", num));
        }
        if(num>=1){
            System.out.print("String.format(\"%.3f\", num)："+String.format("%.3f", num));
        }
        if(Double.valueOf(String.format("%.4f", num))!=0){
            System.out.println("Integer.valueOf(String.format(\"%.4f\", num)："+String.format("%.5f", num));
        }
        if(Double.valueOf(String.format("%.5f", num))!=0){
            System.out.println("Integer.valueOf(String.format(\"%.5f\", num)："+String.format("%.6f", num));
        }
        if(Double.valueOf(String.format("%.6f", num))!=0){
            System.out.println("Integer.valueOf(String.format(\"%.6f\", num)："+String.format("%.8f", num));
        }
        System.out.println("Integer.valueOf(String.format(\"%.77f\", num)："+String.valueOf(num));
    }
}
