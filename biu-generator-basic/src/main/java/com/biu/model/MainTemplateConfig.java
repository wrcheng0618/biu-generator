package com.biu.model;

import lombok.Data;

/**
 * @author: wrcheng
 * @date: 2024年11月26日 22:26
 * @description:
 */
@Data
public class MainTemplateConfig {

    /**
     * 是否生成循环
     */
    private boolean loop;
    /**
     * 作者注释
     * 给个默认值，防止字符串变量在不设置时会报错
     * 或者使用FreeMarker的默认值操作符${author!''}
     */
    private String author = "wrcheng";
    /**
     * 输出信息
     */
    private String outputText = "sum = ";
}
