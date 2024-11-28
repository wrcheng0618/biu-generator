package com.biu.generator;

import com.biu.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author: wrcheng
 * @date: 2024年11月26日 22:31
 * @description:
 */
public class DynamicGenerator {

    private static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        // 新建（new）出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在路径
        File inputFile = new File(inputPath);
        configuration.setDirectoryForTemplateLoading(inputFile.getParentFile());

        // 指定模板文件使用的字符集
        configuration.setDefaultEncoding("UTF-8");

        // 指定数字格式
        configuration.setNumberFormat("0.######");

        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate(inputFile.getName());

        // 生成
        Writer out = new FileWriter(outputPath);
        template.process(model, out);

        // 生成文件后别忘了关闭哦
        out.close();
    }

    public static void main(String[] args) throws IOException, TemplateException {
        // 指定模板文件所在的路径（输出：当前工作目录：D:\idea\biu-generator）
        String projectPath = System.getProperty("user.dir");
        System.out.println("当前工作目录：" + projectPath);
        // 由于工作目录是D:\idea\biu-generator，因此这么写会报FileNotFoundException
        // String inputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String inputPath = projectPath + File.separator + "biu-generator-basic/src/main/resources/templates/MainTemplate.java.ftl";

        String outputPath = projectPath + File.separator + "biu-generator-basic/MainTemplate.java";

        // 创建数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("biu");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("求和结果：");

        doGenerate(inputPath, outputPath, mainTemplateConfig);
    }
}
