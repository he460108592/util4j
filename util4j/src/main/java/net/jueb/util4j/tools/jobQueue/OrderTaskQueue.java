package net.jueb.util4j.tools.jobQueue;

/**
 * 顺序任务执行队列
 * @author Administrator
 */
public class OrderTaskQueue {
	private static TaskRunnerWatcher watcher;//公共运行者监视器
	private final TaskRunner runner;//运行者
	private final String name;
	{
		if(watcher==null)
		{
			watcher=new TaskRunnerWatcher();
			watcher.setName("OrderTaskQueueWatcher");
			watcher.setDaemon(true);
			watcher.start();
		}
	}

	public OrderTaskQueue(String name) {
		this.name=name;
		runner=new TaskRunner(name,watcher);
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addTask(Runnable task)
	{
		runner.addTask(task);
	}
	
	public void start()
	{
		runner.start();
	}
	
	public void stop()
	{
		runner.shutdown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		OrderTaskQueue jo=new OrderTaskQueue("TestJobQueue");
		jo.start();
		for(int i=0;i<1000;i++)
		{
			final int x=i;
			Thread.sleep(5000);
			jo.addTask(new Runnable() {
				@Override
				public void run() {
					System.out.println("x="+x);
					if(x==5)
					{
						try {
							Thread.sleep(11000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				@Override
				public String toString() {
					return x+"";
				}
			});
		}
	}
}