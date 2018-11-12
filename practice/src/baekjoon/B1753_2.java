package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1753_2 {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);

        ALGO solver = new ALGO();
        solver.solve(1, in, out);

        //while (solver.solve(1, in, out)) {
/*
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            solver.solve(1, in, out);
        }
*/
        out.close();
    }
}
class ALGO {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int V = in.nextInt();
        int E = in.nextInt();
        int S = in.nextInt() - 1;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<Integer>());
        for (int i = 0; i < E; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int w = in.nextInt();
            adj.get(u).add(v * 11 + w);
        }
        int[] d = new int[V];
        int MAX_VAL = (int) 1e9;
        Arrays.fill(d, MAX_VAL);
        d[S] = 0;
        int qx = 1;
        int[] qv = new int[2 * E];
        int[] qd = new int[2 * E];
        qv[0] = S;
        qd[0] = 0;
        for (int i = 0; i < qx; i++) {
            int v = qv[i];
            int vd = qd[i];
            if (vd != d[v]) continue;
            for (int a: adj.get(v)) {
                int av = a / 11;
                int aw = a % 11;
                if (vd + aw < d[av]) {
                    d[av] = vd + aw;
                    qv[qx] = av;
                    qd[qx] = d[av];
                    qx++;
                }
            }
        }
        for (int i = 0; i < V; i++) {
            out.println(d[i] == MAX_VAL ? "INF" : d[i]);
        }
    }
}

class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }
}
