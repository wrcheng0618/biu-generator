package com.biu.generator;

import com.biu.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author: wrcheng
 * @date: 2024年11月28日 22:51
 * @description:
 */
public class MainGenerator {

    public static void doGenerate(Object model) throws TemplateException, IOException {
        // 整个项目的根路径
        File parentFile = new File(System.getProperty("user.dir"));

        // 输入路径
        String inputPath = new File(parentFile, "biu-generator-demo-projects/acm-template").getAbsolutePath();
        // 输出路径
        String outputPath = new File(parentFile, "biu-generator-basic").getAbsolutePath();
        // 生成静态文件
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);

        // 输入路径
        String inputDynamicsFilePath = parentFile + File.separator + "biu-generator-basic/src/main/resources/templates/MainTemplate.java.ftl";
        // 输出路径
        String outputDynamicsFilePath = parentFile + File.separator + "biu-generator-basic/acm-template/src/com/biu/acm/MainTemplate.java";
        // 生成动态文件
        DynamicGenerator.doGenerate(inputDynamicsFilePath, outputDynamicsFilePath, model);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("尊姓大名");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("result:");
        doGenerate(mainTemplateConfig);
    }
}
