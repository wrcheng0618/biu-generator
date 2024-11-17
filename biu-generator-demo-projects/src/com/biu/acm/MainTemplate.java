package com.biu.acm;

import java.util.Scanner;

/**
 * @author: wrcheng
 * @date: 2024年11月17日 20:41
 * @description:
 */
public class MainTemplate {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            // 读取输入元素个数
            int n = scanner.nextInt();

            // 读取数组
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            // 处理问题逻辑，根据需要进行输出
            // 示例：计算数组元素的和
            int sum = 0;
            for (int num : arr) {
                sum += num;
            }

            System.out.println("Sum: " + sum);
        }

        scanner.close();
    }
}
