package java0.conc0303;

/**
 * @author chenxiaokang
 * @date 2021/5/26
 */
public class FiboUtil {
    public static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
