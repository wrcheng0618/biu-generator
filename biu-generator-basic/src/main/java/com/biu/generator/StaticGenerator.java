package com.biu.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author: wrcheng
 * @date: 2024年11月24日 21:12
 * @description:
 */
public class StaticGenerator {

    /**
     * 使用Hutool工具类复制文件
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     * 优点：简单
     * 缺点：不够灵活，只能生成完整的目录，如果想忽略目录中的某个文件，就要生成后再删除，损耗性能
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }

    /**
     * 递归拷贝文件（递归实现，会将输入目录完整拷贝到输出目录下）
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByRecursive(String inputPath, String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile, outputFile);
        } catch (Exception e) {
            System.err.println("文件复制失败");
            e.printStackTrace();
        }
    }

    /**
     * 文件 A => 目录 B，则文件 A 放在目录 B 下
     * 文件 A => 文件 B，则文件 A 覆盖文件 B
     * 目录 A => 目录 B，则目录 A 放在目录 B 下
     * 核心思路：先创建目录，然后遍历目录内的文件，依次复制
     * @param inputFile 输入文件/目录
     * @param outputFile 输出文件/目录
     * @throws IOException IO异常
     */
    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        if (inputFile.isFile()) {
            // 文件，直接复制到目标目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        } else {
            // 目录，先创建目录，然后遍历目录内的文件，依次复制
            File destOutputFile = new File(outputFile, inputFile.getName());
            // 如果是目录，首先创建目标目录
            if (!destOutputFile.exists()) {
                destOutputFile.mkdirs();
            }

            // 获取目录下的所有文件和子目录
            File[] files = inputFile.listFiles();
            if (files == null) {
                // 无子文件，直接结束
                return;
            }
            for (File file : files) {
                // 递归拷贝下一层文件
                copyFileByRecursive(file, destOutputFile);
            }
        }
    }

    public static void main(String[] args) {
        // 项目的根目录
        String projectPath = System.getProperty("user.dir");
        File projectFile = new File(projectPath);

        // 输入路径：ACM示例代码模板目录
        String inputPath = new File(projectFile, "biu-generator-demo-projects/acm-template").getAbsolutePath();
        System.out.println("输入路径：" + inputPath);
        // 输出路径：直接输出到项目的根目录
        String outputPath = projectPath;
        System.out.println("输出路径：" + outputPath);
        // copyFilesByHutool(inputPath, outputPath);

        inputPath = "C:\\Users\\wrcheng\\Desktop\\a";
        outputPath = "C:\\Users\\wrcheng\\Desktop\\b";
        copyFilesByRecursive(inputPath, outputPath);
    }
}
