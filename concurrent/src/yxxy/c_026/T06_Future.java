/**
 * 认识future
 */
package yxxy.c_026;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 操作步骤：
 * 一个类实现Callable接口，重新call方法；
 * 在调用的时候，需要使用FutureTask这个类的有参构造，然后再使用thread的有参构造。
 */
public class T06_Future {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/***
		 * new 了一个Callable对象 外面封装了一个FutureTask
		 * Thread 启动的时候 不能往里面扔Callable 只能扔FutureTask
		 * Callable 没有get方法接收返回值
		 */
		FutureTask<Integer> task = new FutureTask<>(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1000;
		}); //new Callable () { Integer call();}
		
		new Thread(task).start();
		
		System.out.println(task.get()); //阻塞
		
		//*******************************
		ExecutorService service = Executors.newFixedThreadPool(5);
		Future<Integer> f = service.submit(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1;
		});
		System.out.println(f.get());
		System.out.println(f.isDone());
		
	}
}
