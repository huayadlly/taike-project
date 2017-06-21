package com.mouse.fixrate;


import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by huayandong on 17/6/21.
 */
public class CountUtils {

    public static List<Double> calculate(int... list) {
        List<Integer> intList = Lists.newArrayList();
        for (int i : list) {
            intList.add(i);
        }
        return calculate(intList);
    }

    public static List<Double> calculate(List<Integer> list) {

        List<Double> resultList = Lists.newArrayList();

        double sum = 0;
        for (int i : list) {
            sum += i;
        }

        for (Integer i : list) {
            double v = handleResult(i / sum);
            resultList.add(v);
        }
        return checkResult(resultList);

    }

    public static double handleResult(double handleNum) {

        BigDecimal bd = new BigDecimal(handleNum);
        return bd.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    public static List<Double> checkResult(List<Double> result) {


        double sum = getSum(result);
        System.out.println("总和：" + sum);

        if (sum > 1.0) {
            double max = Collections.max(result);
            ListIterator listIterator = result.listIterator();
            while (listIterator.hasNext()) {
                if (Double.valueOf(String.valueOf(listIterator.next())) == max) {
                    listIterator.remove();
                    listIterator.add(handleResult(max - (sum - 1.0)));
                }
                double afterSum = getSum(result);
                if (afterSum == 1.0) {
                    return result;
                }
            }
            return result;

        } else if (sum < 1.0) {
            double min = Collections.min(result);
            ListIterator listIterator = result.listIterator();
            while (listIterator.hasNext()) {
                if (Double.valueOf(String.valueOf(listIterator.next())) == min) {
                    listIterator.remove();
                    listIterator.add(handleResult(min + (1.0 - sum)));
                }
                double afterSum = getSum(result);
                if (afterSum == 1.0) {
                    return result;
                }
            }
            return result;
        } else {
            return result;
        }
    }

    public static double getSum(List<Double> list) {
        double afterSum = 0;
        for (double item : list) {
            System.out.println(item);
            afterSum += item;
        }
        return afterSum;
    }


    public static void main(String[] args) {
//        List<Double> calculate = calculate(33, 54, 77, 97, 11, 13, 17, 23, 29);
//        List<Double> calculate = calculate(3, 5, 7, 9, 11, 13, 17, 29, 23);
//        List<Double> calculate = calculate(3, 3, 4, 4, 7);
        List<Double> calculate = calculate(3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3);

        System.out.println(calculate);
    }
}
