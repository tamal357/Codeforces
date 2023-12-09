import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Cf159Div2E {


    static String[] sTakeInputs;
    static int sInputPos = 0;
    static BufferedReader sBufferedReader;
    static StringBuilder sOutputBuilder;

    static int[] scanIntArray() throws Exception{
        String[] inputs = sBufferedReader.readLine().trim().split(" ");
        int[] arr = new int[inputs.length];
        for(int i = 0; i<inputs.length; i++) arr[i] = Integer.parseInt(inputs[i]);
        return arr;
    }

    static long[] scanLongArray() throws Exception{
        String[] inputs = sBufferedReader.readLine().trim().split(" ");
        long[] arr = new long[inputs.length];
        for(int i = 0; i<inputs.length; i++) arr[i] = Long.parseLong(inputs[i]);
        return arr;
    }

    static String[] scanStringArray() throws Exception {
        return sBufferedReader.readLine().trim().split(" ");
    }

    static int scanInt() throws Exception{
        return Integer.parseInt(sBufferedReader.readLine().trim());
    }

    static long scanLong() throws Exception{
        return Long.parseLong(sBufferedReader.readLine().trim());
    }

    static int nextInt() throws Exception {
        if(sInputPos == 0) sTakeInputs = sBufferedReader.readLine().trim().split(" ");
        if(sTakeInputs.length <= sInputPos) {
            sTakeInputs = sBufferedReader.readLine().trim().split(" ");
            sInputPos = 0;
        }
        return Integer.parseInt(sTakeInputs[sInputPos++]);
    }

    static long nextLong() throws Exception {
        if(sInputPos == 0) sTakeInputs = sBufferedReader.readLine().trim().split(" ");
        if(sTakeInputs.length <= sInputPos) {
            sTakeInputs = sBufferedReader.readLine().trim().split(" ");
            sInputPos = 0;
        }
        return Long.parseLong(sTakeInputs[sInputPos++]);
    }

    static String nextString() throws Exception {
        if(sInputPos == 0) sTakeInputs = sBufferedReader.readLine().trim().split(" ");
        if(sTakeInputs.length <= sInputPos) {
            sTakeInputs = sBufferedReader.readLine().trim().split(" ");
            sInputPos = 0;
        }
        return sTakeInputs[sInputPos++];
    }

    static void print(int val, char ch) {
        sOutputBuilder.append(val).append(ch);
    }

    static void print(long val, char ch) {
        sOutputBuilder.append(val).append(ch);
    }

    static void print(float val, char ch) {
        sOutputBuilder.append(val).append(ch);
    }

    static void print(double val, char ch) {
        sOutputBuilder.append(val).append(ch);
    }

    static void print(String val, char ch) {
        sOutputBuilder.append(val).append(ch);
    }

    static void print(String val) {
        sOutputBuilder.append(val);
    }
    static void print(StringBuilder val, char ch) {
        sOutputBuilder.append(val).append(ch);
    }

    static void print(StringBuilder val) {
        sOutputBuilder.append(val);
    }

    static void print(BigInteger integer, char ch) {
        sOutputBuilder.append(integer).append(ch);
    }

    static void print(int x) {
        sOutputBuilder.append(x);
    }

    static void print(char ch) {
        sOutputBuilder.append(ch);
    }

    static void printf() {
        System.out.print(sOutputBuilder);
        sOutputBuilder = new StringBuilder();
    }

    static class Node {
        HashMap<Character, Node> childs = new HashMap<>();
        long endCount = 0L;
    }

    static void buildTrie(String s, Node start) {
        Node temp = start;
        for(int i = 0; i<s.length(); i++) {
            char ch = s.charAt(i);
            temp = temp.childs.computeIfAbsent(ch, k -> new Node());
        }
        temp.endCount++;
    }

    static long query(char[] s, Node start) {
        Node temp = start;
        long count = 0;
        for(int i = 0; i<s.length; i++) {
            char ch = s[i];
            Node cur = temp.childs.get(ch);
            if(cur == null) break;
            long val = cur.endCount*2L*(long)(i+1);
            count += val;
            temp = cur;
        }
        return count;
    }

    static void reverse(char[] arr) {
        for(int i = 0, j = arr.length-1; i<j; i++,j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    static void solve() throws Exception {
        int n = nextInt();
        String[] arr = new String[n];
        long totalCount = 0;
        Node start = new Node();
        for(int i = 0; i<n; i++) {
            arr[i] = nextString();
            totalCount += 2L*(long)n*(long)arr[i].length();
            buildTrie(arr[i], start);
        }
        long deduct = 0;
        for(int i = 0; i<n; i++) {
            char[] chars = arr[i].toCharArray();
            reverse(chars);
            deduct += query(chars, start);
        }
        print(totalCount-deduct,'\n');
    }

    public static void main (String[] args) throws Exception {
        sBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        sOutputBuilder = new StringBuilder();
        int t = 1;//nextInt();
        while(t>0) {solve(); t--;}
        printf();
        sBufferedReader.close();
    }
}
