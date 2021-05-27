package java0.conc0303;

import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class Homework03 {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        int result = sum(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程

        AbsThreadExecuteDemo demo1 = new AbsThreadExecuteDemo(1) {

            private FutureTask task = new FutureTask(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    printStarted();
                    return sum();
                }
            });

            @Override
            public void runChildThread() {
                new Thread(this.task).start();
            }

            @Override
            public void runInMainThread() {
                try {
                    printResult((Integer) this.task.get());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        };
        demo1.runChildThread();
        demo1.runInMainThread();

        //第二种join

        AbsThreadExecuteDemo demo2 = new AbsThreadExecuteDemo(2) {
            Integer sum = null;

            @Override
            public void runChildThread() {
                Thread task = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        printStarted();
                        sum = sum();
                        printStop();
                    }
                });
                task.start();
                try {
                    task.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void runInMainThread() {
                printResult(sum);
            }
        };

        demo2.runChildThread();
        demo2.runInMainThread();


        AbsThreadExecuteDemo demo3 = new AbsThreadExecuteDemo(3) {
            Integer sum = null;
            Object lock = new Object();
            @Override
            public void runChildThread() {
                Thread task = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(this.getClass());

                        synchronized (lock){
                            printStarted();
                            sum = sum();
                            printStop();
                            lock.notifyAll();
                        }



                    }
                });
                task.start();
            }

            @Override
            public void runInMainThread() {
                try {

                    synchronized (lock){
                        lock.wait();
                        printResult(sum);
                    }
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        };
        demo3.runChildThread();
        demo3.runInMainThread();
        AbsThreadExecuteDemo demo4 = new AbsThreadExecuteDemo(4) {
            Integer sum = null;
            CountDownLatch countDownLatch = new CountDownLatch(1);

            @Override
            public void runChildThread() {
                Thread task = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        printStarted();
                        sum = sum();
                        printStop();
                        countDownLatch.countDown();
                    }
                });
                task.start();
            }

            @Override
            public void runInMainThread() {
                try {
                    countDownLatch.await();
                    printResult(sum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        };
        demo4.runChildThread();
        demo4.runInMainThread();

        AbsThreadExecuteDemo demo5=new AbsThreadExecuteDemo(5) {
            Integer sum=null;
            CyclicBarrier cyclicBarrier=new CyclicBarrier(1,new Runnable(){
                @Override
                public void run() {
                    runInMainThread();
                }
            });
            @Override
            public void runInMainThread() {
                printResult(sum);

            }

            @Override
            public void runChildThread() {
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        printStarted();
                        sum=sum();
                        printStop();
                        try {
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        };

        demo5.runChildThread();

    }


    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
