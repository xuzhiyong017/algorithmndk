package com.sky.algorithmndk;

/**
 * @author: xuzhiyong
 * @date: 21-1-5  下午3:48
 * @Email: 18971269648@163.com
 * @description:
 */
public class Algorithm {

    public int myAtoi(String s) {
        int index = 0;
        int len = s.length();
        long res = 0;

        if(index == len){
            return 0;
        }

        while( index < len && s.charAt(index) == ' '){
            index++;
        }

        if(index == len){
            return 0;
        }
        int negative = 1;

        if(s.charAt(index) == '-'){
            index++;
            negative = -1;
        }else if(s.charAt(index) == '+'){
            index++;
        }

        if(index == len){
            return 0;
        }

        while( index < len){
            char c = s.charAt(index++);
            if(c < '0' || c > '9'){
                break;
            }
            res = res * 10 + (c - '0');

            if(res* negative > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }else if(res * negative < Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
        }
        return (int)res*negative;
    }

}
