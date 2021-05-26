package java0.conc0303;

/**
 * @author chenxiaokang
 * @date 2021/5/26
 */
public abstract class AbstractThreadDemo {
    private int options;
    public AbstractThreadDemo(int options){
        this.options=options;
    }
    public  void runChildThread(Thread thread){
        beforeThread();
        thread.start();
        afterThread();
    }
    public void beforeThread(){
        System.out.println("子线程执行");
    }
    public void afterThread(){
        System.out.println("子线程执行");
    }
}
