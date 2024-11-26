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

    public static void main(String[] args) throws IOException, TemplateException {
        // 新建（new）出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径（输出：当前工作目录：D:\idea\biu-generator）
        String projectPath = System.getProperty("user.dir");
        System.out.println("当前工作目录：" + projectPath);
        File projectFile = new File(projectPath);
        // 由于工作目录是D:\idea\biu-generator，因此这么写会报FileNotFoundException
        // configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        configuration.setDirectoryForTemplateLoading(new File(projectFile, "biu-generator-basic/src/main/resources/templates"));

        // 指定模板文件使用的字符集
        configuration.setDefaultEncoding("UTF-8");

        // 指定数字格式
        configuration.setNumberFormat("0.######");

        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate("MainTemplate.java.ftl");

        // 创建数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("biu");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");

        // 生成
        Writer out = new FileWriter(new File(projectFile, "biu-generator-basic/MainTemplate.java"));
        template.process(mainTemplateConfig, out);

        // 生成文件后别忘了关闭哦
        out.close();
    }
}
