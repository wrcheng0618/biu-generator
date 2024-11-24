package com.biu.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;

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
        copyFilesByHutool(inputPath, outputPath);
    }
}
