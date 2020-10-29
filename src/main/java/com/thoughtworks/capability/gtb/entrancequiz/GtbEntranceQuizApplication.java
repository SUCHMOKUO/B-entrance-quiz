package com.thoughtworks.capability.gtb.entrancequiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO GTB-工程实践: * 分包合理
// TODO GTB-知识点: * 使用了三层架构
// TODO GTB-知识点: * API设计符合Restful实践
// TODO GTB-知识点: * 使用了lombok
// TODO GTB-完成度: * 没有实现分组相关的接口
// TODO GTB-测试: * 有Controller层测试，且覆盖了已完成的功能
@SpringBootApplication
public class GtbEntranceQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(GtbEntranceQuizApplication.class, args);
	}

}
