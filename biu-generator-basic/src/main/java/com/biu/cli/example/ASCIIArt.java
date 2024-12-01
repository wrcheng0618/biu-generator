package com.biu.cli.example;

import cn.hutool.core.util.ArrayUtil;
import picocli.CommandLine;

/**
 * @author: wrcheng
 * @date: 2024年11月30日 22:09
 * @description: 代码解释：
 * 1、创建一个实现 Runnable 或 Callable 接口的类，这就是一个命令；
 * 2、使用 @Command 注解标记该类并为其命名，mixinStandardHelpOptions 属性设置为 true 可以给应用程序自动添加 --help 和 --version选项；
 * 3、通过 @Option 注解将字段设置为命令行选项，可以给选项设置名称和描述；
 * 4、通过 @Parameters 注解将字段设置为命令行参数，可以指定默认值、描述等信息；
 * 5、Picocli 会将命令行参数转换为强类型值，并自动注入到注解字段中；
 * 6、在类的 run 或 call 方法中定义业务逻辑，当命令解析成功（用户按下回车键）后被调用；
 * 7、在 main 方法中，通过 CommandLine 对象的 execute 方法来处理用户输入的命令，剩下的就交给 Picocli 框架来解析命令并执行业务逻辑了；
 * 8、CommandLine.execute 方法返回一个退出代码，可以调用 System.exit 方法并将退出代码作为参数，从而调用进程表示成功或失败；
 */
@CommandLine.Command(name = "ASCIIArt", version = "ASCIIArt 1.0", mixinStandardHelpOptions = true)
public class ASCIIArt implements Runnable {

    @CommandLine.Option(names = {"-s", "--font-size"}, description = "Font size")
    int fontSize = 19;

    @CommandLine.Parameters(paramLabel = "<word>", defaultValue = "Hello, picocli",
            description = "Words to be translated into ASCII art.")
    private final String[] words = {"Hello,", "picocli"};

    @Override
    public void run() {
        // 自己实现业务逻辑
        System.out.println("fontSize = " + fontSize);
        System.out.println("words = " + ArrayUtil.join(words, ","));
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ASCIIArt()).execute(args);
        System.exit(exitCode);
    }
}
