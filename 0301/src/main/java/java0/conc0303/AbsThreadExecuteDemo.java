package java0.conc0303;

/**
 * @author chenxiaokang
 * @date 2021/5/26
 */
public abstract class AbsThreadExecuteDemo{
    private int options;
//    protected  T childTaskHolder ;

    public AbsThreadExecuteDemo(int options){
        this.options=options;
//        this.childTaskHolder=target;
    }
    protected String prefix(){
        return "第"+options+"种,";
    }

    /**
     * 启动新的现场执行
     * @throws InterruptedException
     */
    public  abstract void runChildThread() ;


    /**
     * 在主线程中执行
     */
    public abstract void runInMainThread();

    public void printStarted(){
        System.out.println(prefix()+"子线程started");
    }
    public void printRunning(){
        System.out.println(prefix()+"子线程running");
    }
    public void printStop(){
        System.out.println(prefix()+"子线程stop");
    }
    public void printResult(Integer result){
        System.out.println(prefix()+" 回到主线程,结果是"+result);
    }
}
