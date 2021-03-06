package net.jueb.util4j.queue.queueExecutor.groupExecutor;

import java.util.Iterator;

import net.jueb.util4j.queue.queueExecutor.QueueFactory;
import net.jueb.util4j.queue.queueExecutor.executor.QueueExecutor;
import net.jueb.util4j.queue.queueExecutor.groupExecutor.QueueGroupExecutor.KeyElement;

/**
 * 队列组
 * @author juebanlin
 */
public interface KeyQueueGroupManager extends Iterable<QueueExecutor>{
	
	public boolean hasQueueExecutor(String key);
	
	/**
	 * 获取任务执行器,此队列的名字等于队列别名
	 * @param queue
	 * @return
	 */
	public QueueExecutor getQueueExecutor(String key);

	/**
	 * 迭代执行器
	 */
	@Override
	public Iterator<QueueExecutor> iterator();
	
	public Iterator<KeyElement<QueueExecutor>> keyIterator();
	
	public long getToalCompletedTaskCount();
	
	public long getToalCompletedTaskCount(String key);
	
	public void setGroupEventListener(KeyGroupEventListener listener);
	
	public QueueFactory getQueueFactory();
	
	public static interface KeyGroupEventListener{
		/**
		 * 某队列的处理任务
		 * @param task
		 */
		public void onQueueHandleTask(String key,Runnable handleTask);
	}
}
