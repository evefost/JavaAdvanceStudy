多线程
6.1 概念理解
    1、进程：
    一个正在执行的程序，如我们在windows任务管理器中所看到的就是一个个的进程，每一个进程执行都有一个执行顺序，该顺序是一个执行路径，或者叫一个控制单元；
    2、线程：就是进程中的一个独立的控制单元，
    线程在控制着进程执行；一个进程中至少有一个线程。java vm 启动的时候会有一先个进程java.exe.。该进程中至少一个线程负责java程序的执行。而且这个线程运行的代码存在于main方法中；该线程称之为主线程。
    3、扩展：其实更细节说明jvm，jvm启动不止一个线程，还有一个垃圾回收线程
6.2 意义：能使同进执行多个任。提高应用的效率
6.2创建线程
    1、创建线程的第一种方式：
    通过对API的查找，java已经提供了对线程这类事物的描述，就Thread类。
    a、定义成Thread的子类
    b复写其中的run方法
	目的：将自定义代码存储在run方法，让线程运行。
    c、调用start方法启动线程。
    2、运行效果结论：
    因为多个线程都获取cpu的执行权，cpu执行到谁，谁就运行。
    明确一点，在某一时亥，只能有一个程序在运行，只是cpu在做着快速切换，
    以达到看上去是同时运行的效果；我们可以形象把多线程的运行行为在互相
    抢夺cpu的执行权；这就是多线程的一个特性：随机性，谁抢到，谁执行，至
    于执行多长时间由cpu决定
    3、为什么要覆盖run方法？
    Thread类用于描述线程。
    该类定义一个功能，用于存储线程要运行的代码，该存储功能就是run方法。
    也就是说Thread中的run方法，用于存储线程序运行的代码。
    练习：
    创建两个线程，和主线程交替运行。
    原来线程都有自己的默认名称。
    Thread-编号 该编号从0开始.
    static Thread currentThread() ：获取当前线程对象
    getName():获取线程的名称。
    设置线程名称：setName或者构造函数。
    需求：简单的卖票程序。
    多个窗口同时买票
    4、创建线程的第二种方式：实现Runnable接口
    步骤：
    a、定义类实现Runnable接口
    b、覆盖Runnable接口的run的方法。
		将线程需要运行的代码放在该run方法中。
    c、通过Thread类建立线程对象
    d、将Runnable接口的子类对象作为实际参数传递给Thread类的构造函数
		为什么时候要将Runnable接口的子类对象传递给Thread的构造函数？
		因为，自定义的run方法所属的对象是Runnable接口的子类对象。
		所以要让线程去指定对象的run方法，就必须明确该方法所属的对象。
    e、调用Thread类的start方法开启线程自动调用Runnable接口子类run方法
	5、自定义线程名称及设置线程名称
		a、在查查阅API时发现Thread有两个这样的构造函数：
    Thread(String name)和Thread target,name);
    所以我们可参建立子对象时把自定义线程名称专入；
    示例：
    class ZiThread extends Thread{
				public ZiThread(String name){
					super(name);//把自定义名称传给父类
				}
				public ZiThread(Runnable runnable,String name){
					super(runnable,name);
				@Override
				public void run() {
				System.out.println(Thread.currentThread().getName());
				}
    }
		b、设置线程名称setName。
6.3、实现方式与继承方式有什么区别呢？
    1、实现方式好处：避免了单继承的局限性。
    在定义线程时，建议使用实现
    2、继承Thread:线程代码存放在Thread子类的run方法中。
    实现Runnable：线程代码存在接口的子类的run方法中；
6.4、线程的四种状态：

6.4 线程的安全：
    1、安全问题的产生：当多条语句操作同多个线程的共享数数时，一个线程对多条语
    句只执行了一部分，还没有执行完，另外一个线程也参与进来，导致共享数据的错乱；
    2、解决方式：
    a、分析：如果我们可以控制一个线程还没执行完（操作共享数据的代码），
    别的线程就不能进来，问题就得已解决了。
	b、采用同步代码块
    synchronized(锁){
			需要被同步的代码
    }
    对象就像一把锁，持有锁的线程可以在同步块中执行，没有持有锁的线程
    即使获取cpu的执行权，也进不去，因为没有获取锁。
    如火车上的卫生间。
    3、同步的前提：
    a、必须要有两个或者两个以上的线程。
    b、必须是多个线程共同使用同一个锁.
    4、同步利弊：
    好处：解决了多线程的安全问题
    弊端：多个线程需要判断锁，较为消耗资源，
6.5查找线程可能出现的安全问题：
	1、明确那些代码是多线程运行代码；
	2、明确共享数据；
	3、明确多线程运行代码中那些语句是操作共享数据的。
6.6同步中所用的锁
	1、同步代码块要指定某个锁
    2、同步函数所使用的是哪个锁？
		函数需要被对象调用，那么函数都有一个所属对象引用，就是this.
		所以同步函数使用的是this.；疑问：如果两个线所执行的是两个子类
    的实例，那么这里不就有两个不同的锁了，分别锁住两份相同的代码了？
	3、如果同步函数被静态修饰，使用的是哪个锁？
		经过验证，发现不是this,因为静态方法中也不可以定义this;静态进入存时，
    没有本类对象，所以这时的锁是本类字节码所属文件的对象这个锁
6.7线程同步示例：
	1、同步代码块：
		Synchronized(锁){
			须要必同步的代码；
		}
    2、同步函数：
    非静态使用的是this锁
	public synchronized fun(){
		需要被同步的代码
    }
    静态使用的是本类字节码所属的文件对象；
    static synchronized  void fun(){
			需要被同步的代码；
    }
6.8同步死锁：同步中嵌套同步就有可能出现死锁
6.9线程间通信
	1、共享资源操作示图

    其实就是多个线程在操作同一个资源。
	2、wait()、notify()、notifyAll()这些操作：？？？
    都使用在同步中，因为要对持有监视（锁）的线程操作
    所以要使用在同步中，因为中有同步才具有锁
    3、为什么在Object中定义了wait()、notify()、notifyAll()这些操作？
    a、因为这些方法都用在操作同步中线程中，都必须要标识它们操作线程只有锁，
    b、只有在同一个锁上的被等待线程，才可以被同一个锁上notify唤醒，
    不可以对不同锁中的线程进行唤醒，也就是说，等待和唤醒必须是同一个锁；
    c、锁可以是任意对象,任意对象的代码都可以被同步，所以可以被任意对象调用
    的方法定义在Object类中。
总之：这些方法是被锁操作的，锁可以是任意对象；但只有在同步中才有锁，也就是说这些方法只使用在同步中（好像这？样说也不对？？）
    理解：
    当某资源被某个锁锁上后，那么要访问该资的线程，必须先获取到锁上该资源的锁才能对该资源进行访问，当线程访问完后会自动释放所获得的锁.
6.10 JDK1.5中提供了多线程升级解决方案
1、将同步Synchronized替换成现实Lock操作：
		lock.lock();//当前线程锁住下面代码
			被上锁的代码块；
		lock.unlock();//当前线程把锁释放；
2、Condition里面的方法用于代替Object的wait(),notify(),notifyAll()方法；
    Condition是被Lock使用的，通常是把Condition对象绑定到某个锁上;
3、示例：
6.11停止线程
1、stop方法已经过时。
2、只有一种，run方法结束。
开启多线程运行，运行代码通常是循环结构
只要控制住循环，就可以让run方法结束，也就是线程结束。
3、特殊情况：
当线程处于了冻结状态，
就不会读取标记，那么线程就不会结束。
当没有指定的方式让冻结的线程恢复到运行状态，这时需要对冻结进行清除。
强制让线程恢复到运行状态中来，这样就可以操作标记让线程结束。
后台线程：当所有前台线程结束后，后台线程自动结束。
4、join:
当A线程执行到了B线程的join（）方法时，A就会等待，等B线程都执行完，A才会执行。
join可以用来临时加入线程执行。
示例：
package cn.itcast.thread;
//对于多个生产者和消费者同步操作
import java.util.concurrent.locks.*;
class ProducerConsumerDemo2{
	public static void main(String[] args) {
		Resource r =new Resource();
		Producer pro = new Producer(r);
		Consumer con = new Consumer(r);

		Thread t1 = new Thread(pro);
		Thread t2 = new Thread(pro);
		Thread t3 = new Thread(con);
		Thread t4 = new Thread(con);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
class Resource{
	private String name;
	private int count =1;
	private boolean flag = false;
	private Lock lock = new ReentrantLock();
	//创建个Condition对象，这两个对象是绑定到产生自己的锁对象的。
	private Condition condition_pro = lock.newCondition();//控制生产者
	private Condition condition_con = lock.newCondition();//控制消费者

	public  void set(String name) throws InterruptedException {
		lock.lock(); //锁上
		try{
			while(flag)
				//当前线程等待，直到别的线程执行condition_pro.signal()
				condition_pro.await();
this.name = name+"--"+count++;
			System.out.println(Thread.currentThread().getName()
+"...生产者..."+this.name);
			flag = true;
			//唤醒在这个条件下等待的其他线程
//(也就是执行了condition_con.await()的线程)
			condition_con.signal();
		}finally{
			lock.unlock(); //开锁 一定要执行
		}
	}
	public void out() throws InterruptedException{
		lock.lock();
		try{
			while(!flag)
//当前线程等待，直到别的线程执行
//condition_com.signal()
				condition_con.await();			System.out.println(Thread.currentThread().getName()
+"..... 消费者......"+this.name);
			flag = false;
			condition_pro.signal();
		}finally{
			lock.unlock(); //开锁 一定要执行
		}
	}
}
//生产线程代码
class Producer implements Runnable{
	private Resource res;
	Producer(Resource res){
		this.res = res;
	}
	public void run() {
		while(true){
			try{
				//操作共享资源
				res.set("+商品+");
			}
			catch (Exception e){}
		}
	}
}
//消费都线程代码
class Consumer implements Runnable{
	private Resource res;
	Consumer(Resource res){
		this.res = res;
	}
	public void run(){
		while(true){
			try{
				//操作共享资源
				res.out();
			}catch (Exception e){}
		}
	}
}
