package com.yhhl.dubbo.service;

import org.springframework.stereotype.Service;

import com.yhhl.wsts.common.TransactionContextMain;



@Service("helloService")
public class HelloServiceImpl implements HelloService{

	@Override
	public String sayHello(TransactionContextMain context,String name) {
		System.out.println("事务主ID是：");
		System.out.println("sayHello()成功调用,name是 "+name);
		return "你好！"+name;
	}

	@Override
	public String sayGoodbye(TransactionContextMain context, String name) {
		System.out.println("sayGoodbye()成功调用,name是 "+name);
		return null;
	}
	
}
