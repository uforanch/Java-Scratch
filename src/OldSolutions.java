/*
Reviewing Revature code so I have easier things to make to get my brain juices full of Java than with Leetcode

* Most common character [*]
* nth number of fibonacci [*]
* largest sum can be obtained from a pair of numbers (not both the same unless dupes) [*]
* Reverse a string [*]
* isogram check (no letter repeated) [*]
-----
* find where number should be inserted in sorted list
* take in one word make pig latin
  * bonus make sentance pig latin
* check if string is palindrome
* return part of array given by start and end (not array list)
* return a map with word count per word
* return how many words in sentence total
* check if two seperate values sum to specific sum
* return if array contains dupe values
* string that has all characters of another string removed
  * use indexOf
* return longest string of array
* combine two arrays into single array and return it
* return true if array sorted
* does not contain
* max minus min
* arrays are equal
* contains
 */

import java.util.*;

public class OldSolutions {
    //for the thing
    public void swap_vars(int a, int b){
        System.out.println(String.format("A %d B %d", a,b));
        a=a+b;
        b=a-b;
        a=a-b;
        System.out.println(String.format("A %d B %d", a,b));
    }

    public int binary_search(int[] A, int N){
        int minLoc = 0;
        int maxLoc = A.length-1;
        while (maxLoc >= minLoc){
            int middle = (minLoc + maxLoc)/2;
            if (A[middle]==N){
                return middle;
            } else if (A[middle] > N){
                maxLoc=middle-1;
            } else {
                minLoc=middle+1;
            }
        }
        return -1;
    }

    public void bubble_sort(int[] arr){
        for (int i=0; i<arr.length; i++){
            for (int j=0; j<arr.length-i-1;j++){
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }


    //most common char
    public char MostCommonCharacter(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        char max_ch = s.charAt(0);
        int max_ch_c = 0;
        for (char c : s.toUpperCase().toCharArray()) {
            if (counter.containsKey(c)) {
                counter.put(c, counter.get(c) + 1);
            } else {
                counter.put(c, 0);
            }
            if (counter.get(c)> max_ch_c){
                max_ch = c;
                max_ch_c = counter.get(c);
            }

        }
        return max_ch;

    }

    public int fib(int n){
        if (n==0 || n==1){return 1;}
        if (n<0) { return 0;}
        int f_l = 1;
        int f_ll= 1;
        for(int i=2; i<n+1; i++){
            int f = f_l;
            f_l=f_l+f_ll;
            f_ll=f;
        }
        return f_l;
    }

    public int maxSum(int[] arr){
        int max_l = Math.max(arr[0], arr[1]);
        int max_ll = Math.min(arr[0], arr[1]);
        for (int i=2; i<arr.length; i++){
            if (arr[i]>=max_l){
                max_ll=max_l;
                max_l = arr[i];
            }
        }
        return max_l+max_ll;
    }

    public String reverse_string1(String s){
        StringBuilder sb = new StringBuilder(s);

        return sb.reverse().toString();
    }

    public String reverse_string2(String s){
        char[] c = new char[s.length()];
        for (int i=0; i<s.length(); i++){
            c[i] = s.charAt(s.length()-1-i);
        }
        return String.valueOf(c);
    }

    public boolean isIsogram1(String s){
        Set<Character> hs =  new HashSet<Character>();
        for (char c: s.toLowerCase().toCharArray()){
            hs.add(c);
        }
        return hs.size()==s.length();
    }

    public boolean isIsogram2(String s){
        Set<Character> hs =  new HashSet<Character>();
        for (char c: s.toLowerCase().toCharArray()){
            if (!hs.contains(c)) {
                hs.add(c);
            } else {
                return false;
            }

        }
        return true;


    }
    //------
    //new questions
    public int[] atLeastOnceInts(int[] nums){
        Set<Integer> hs = new HashSet<>();
        for(int n:nums){
            hs.add(n);
        }

        int[] ret = new int[hs.size()];
        int i =0;
        for(int n:hs){
            ret[i]=n;
            i++;
        }
        return ret;
    }

    public int[] onlyOnceInts(int[] nums){
        Map<Integer, Integer> hm = new HashMap<>();
        for(int n:nums){
            if (hm.containsKey(n)){
                //hm.put(n,hm.get(n)+1);
                hm.merge(n, 1, Integer::sum);
            } else {
                hm.put(n, 1);
            }

        }

        List<Integer> al = new ArrayList<>();
        for(int n: hm.keySet()){
            if (hm.get(n)==1){
                al.add(n);
            }
        }
        int[] ret = new int[al.size()];
        for(int i=0;i<al.size();i++){
            ret[i] = al.get(i);
        }
        return ret;
    }

    public int[] mergeSorted(int[] l1, int[] l2){
        int[] ret = new int[l1.length + l2.length];
        int p1=0;
        int p2=0;
        int i=0;
        while (p1<l1.length && p2<l2.length){
            if (l1[p1] <=l2[p2]){
                ret[i]=l1[p1];
                p1++;
            } else {
                ret[i]=l2[p2];
                p2++;
            }
            i++;
        }
        if (p1==l1.length){
            while(p2<l2.length){
                ret[i] = l2[p2];
                p2++;
                i++;
            }

        } else {
            while(p1<l1.length){
                ret[i] = l1[p1];
                p1++;
                i++;
            }
        }
        return ret;
    }



    public static void main(String args[]){
        OldSolutions o = new OldSolutions();
        o.swap_vars(10,-10);
        System.out.println(o.binary_search(new int[]{1,2,4,5}, 3));
        System.out.println(o.binary_search(new int[]{-100,-1,1,2,4,5,7}, 4));
        int[] arr = {6,5,4,3,2,1};
        o.bubble_sort(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println("----------------------");
        System.out.println(o.MostCommonCharacter("aaAAabb") );
        System.out.println(o.fib(2));
        System.out.println(o.maxSum(new int[]{1,2,4,4,1}));
        System.out.println(o.reverse_string1("Hello"));
        System.out.println(o.reverse_string2("Hello"));
        System.out.println(o.isIsogram1("xxx"));
        System.out.println(o.isIsogram1("xyz"));
        System.out.println(o.isIsogram2("xxx"));
        System.out.println(o.isIsogram2("xyz"));

        System.out.println("----------------------");
        int[] arr1 = new int[] {1,1,2,2,4,5,1};
        System.out.println(Arrays.toString(o.atLeastOnceInts(arr1)));
        System.out.println(Arrays.toString(o.onlyOnceInts(arr1)));
        System.out.println(Arrays.toString(o.mergeSorted(new int[]{1,3,5}, new int[]{2,3,4,6,7,8})));


    }

}
