package com.dot;

import com.dot.repo.UserAccessLogRepository;
import com.dot.utils.ReadFromText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//import java.io.FileReader;


@SpringBootApplication
public class DotApplication {
	public static String start,duration,accessFile;
	public static int limit;

	@Autowired
	static
	FileReader fileReader;
	private static UserAccessLogRepository userAccessLogRepository;
	public static void main(String[] args) {

		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(DotApplication.class, args);
		   userAccessLogRepository = configurableApplicationContext.getBean(UserAccessLogRepository.class);

		//configurableApplicationContext.getBean(FTT.class).start();
		//configurableApplicationContext.getBean(FileReader.class).run(args);


		//Read Text from file
		ReadFromText t1 = new ReadFromText(args,userAccessLogRepository);
		Thread t2 = new Thread(new MyRunnable(args,configurableApplicationContext), "t2");
		t1.start();

		//start t2 thread only when first t1 thread is dead
		try {
			t1.join();
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

		t2.start();


	}

	static class MyRunnable implements Runnable{
		private String[] args;
		private ConfigurableApplicationContext configurableApplicationContext;

		public MyRunnable(String[] args, ConfigurableApplicationContext configurableApplicationContext) {
			this.args = args;
			this.configurableApplicationContext = configurableApplicationContext;
		}

		@Override
		public void run() {
			configurableApplicationContext.getBean(FileReader.class).run(args);
		}
	}

 }
