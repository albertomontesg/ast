package util;

/**
 * @author Alberto Montes
 * @date 2-dec-2013
 * @subject AST
 * @exercise Practica8: Implementacio de Protocols // Part 2
 * 			Reliable transmission over a totally reliable channel
 */
public class Modular {

    public static int n;

    public static int next(int m) {
        return (m + 1) % n;
    }

    public static int distance(int f, int l) {
        int d = l - f;
        if (d < 0) {
            d = n + d;
        }
        return d;
    }

    public static int between(int m, int f, int l) {
        int df = distance(f, m);
        int dl = distance(m, l);
        if (df + dl < n) {
            return (df + 1)%n;
        }
        return -1;
    }
}
