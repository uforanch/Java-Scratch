import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/*
Note on streams:
there's max and min but needs comparator
so does sorted if you

There's comparator.comparing()
.reversed() to reverse a comparator

string s.chars().map(c->(char)c).
is how to steram a string's chars


public int[] arrayOnce_00(int[] arr){

        // so this doesn't work without boxed
        // mapping entries to integer doesn't work either

        //TO REMEMBER: HAVE TO MANUALLY BOX AND UNBOX FOR STREAMS
         List<Integer> l =
                 Arrays
                .stream(arr)
                .boxed()//have to box
                .collect(Collectors.toMap(i->i, i->1, (i, j)->i+1)) // function for key, value, collision
                .entrySet().stream()
                .filter(e->(e.getValue()==1))
                .map(e->e.getKey()).collect(Collectors.toList());
        return l.stream().mapToInt(i->i).toArray(); // have to unbox

    }

    sb.insert(5, "!!!?");
        System.out.println(sb.reverse());
 */

public class CodeSolutions {
    //Merge two sorted int Arrays, keeping each value in ascending numberical order
    public int[] mergeArrays_00(int[] arr1, int[] arr2){
        int[] outputArr = new int[arr1.length+arr2.length];
        int p1 = 0;
        int p2 = 0;
        int pm = 0;
        while ( (p1<arr1.length) && (p2<arr2.length)){
            if  (arr1[p1] <= arr2[p2]){
               outputArr[pm] = arr1[p1];
               p1++;
            }  else {
                outputArr[pm] = arr2[p2];
                p2++;
            }
            pm++;
        }
        if (p2!=arr2.length){
            for (int i=p2; i<arr2.length; i++){
                outputArr[pm] = arr2[i];
                pm++;
            }
        } else if (p1!=arr1.length){
            for (int i=p1; i<arr1.length; i++){
                outputArr[pm] = arr1[i];
                pm++;
            }
        }
        return outputArr;
    }


    public int[] mergeArrays_01(int[] arr1, int[] arr2){
        int[] out = new int[arr1.length + arr2.length];
        int i2=0;
        int a2 = arr2[i2];
        int i_out =0;
        for (int i1=0; i1<arr1.length; i1++){
            int a1 = arr1[i1];
            while( (a2<=a1) && (i2<arr2.length)){
                out[i_out]=a2;
                i_out++;
                i2++;
                if (i2>=arr2.length){
                    break;
                } else {
                    a2 = arr2[i2];
                }
            }


                out[i_out] = a1;
                i_out++;


        }
        return out;
    }

    public void mergeArrayLists_00(List<Integer> master, List<Integer> arr){
        int i_out=0;
        int a_m=master.get(i_out);
        for(int a:arr){
            while ( (i_out<master.size()) && (a_m<=a)){
                //hm. If we WERE actually adding master into itself we'd need to increment i past that
                i_out++;
                if (i_out>=master.size()){
                    break;
                }
                a_m = master.get(i_out);
            }
            if (i_out<master.size()) {
                master.add(a,i_out);
                i_out++;
            } else {
                master.add(master.size()-1, a);
            }

        }

    }


    //Find the values in an Array that occur only once
    public int[] arrayOnce_00(int[] arr){

        // so this doesn't work without boxed
        // mapping entries to integer doesn't work either

        //TO REMEMBER: HAVE TO MANUALLY BOX AND UNBOX FOR STREAMS
         List<Integer> l =
                 Arrays
                .stream(arr)
                .boxed()//have to box
                .collect(Collectors.toMap(i->i, i->1, (i, j)->i+1)) // function for key, value, collision
                .entrySet().stream()
                .filter(e->(e.getValue()==1))
                .map(e->e.getKey()).collect(Collectors.toList());
        return l.stream().mapToInt(i->i).toArray(); // have to unbox

    }

    public int[] arrayOnce_01(int[] arr){
        Map<Integer,Integer> hm = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if (hm.containsKey(arr[i])){
                hm.put(arr[i], hm.get(arr[i])+1);
            } else {
                hm.put(arr[i], 1);
            }
        }
        List<Integer> l = new ArrayList<>();
        //oh this is what took a long time to figure out before
        // probably could just do key list
        for (Map.Entry<Integer, Integer> e:hm.entrySet()){
            if (e.getValue()==1){
                l.add(e.getKey());
            }
        }
        int[] output = new int[l.size()];
        for(int i=0; i<output.length;i++){
            output[i] = l.get(i);
        }
        return output;

    }

    public int[] arrayOnce_02(int[] arr){
        Set<Integer> s = new HashSet<>();
        Set<Integer> s_r = new HashSet<>();
        for (int a: arr){
            if (s.contains(a) && !s_r.contains(a)) {
                s.remove(a);
                s_r.add(a);
            } else if (!s.contains(a) && !s_r.contains(a)) {
                s.add(a);
            }
        }
        int[] out = new int[s.size()];
        int i = 0;
        for (int a: s){
            out[i]=a;
            i++;
        }
        return out;
    }
    //Convert an Array of Strings to be all uppercase
    public String[] stringUpper_00(String[] sa){
       List<String> l=Arrays.stream(sa).map(s->s.toUpperCase()).toList();
       return  l.toArray(new String[l.size()]);

    }

    public String[] stringUpper_01(String[] sa){
        String[] sa_2 = new String[sa.length];
        for(int i=0; i<sa.length;i++){
            sa_2[i] = sa[i].toUpperCase();
        }
        return sa_2;
    }

    //Write a method to delete whitespace in a String
    public String deleteWhitespace_00(String s){
        //Arrays.stream(s.toCharArray())
        //giving up on this. Basically do not use streams on characters
        return s.chars().map(c->(char) c).filter(c->!Character.isWhitespace(c)).toString();
    }
    public String deleteWhitespace_01(String s){
        StringBuilder sb = new StringBuilder();
        for(char c:s.toCharArray()){
            if (!Character.isWhitespace(c)){
                sb.append(c);
            }
        }
        return sb.toString();
    }
    //bubble sort
    public void bubblesort_00(int[] arr){
        for(int i0=0;i0<arr.length;i0++){
            for(int i1=i0+1; i1<arr.length; i1++){
                if (arr[i1-1]>arr[i1]){
                    int t=arr[i1];
                    arr[i1]=arr[i1-1];
                    arr[i1-1]=t;
                }
            }
        }
    }

    //linear sort
    public void linearsort_00(int[] arr){

        for(int i0=0; i0<arr.length;i0++){
            for(int i1=i0-1;i1>=0;i1--){
                if (arr[i1]>arr[i1+1]){
                    int t=arr[i1+1];
                    arr[i1+1]=arr[i1];
                    arr[i1]=t;
                } else {
                    break;
                }
            }
        }
    }

    //stuff I didn't know about - random int array
    public int[] randomArray(int l, int min, int max){
        return IntStream.generate(()->new Random().nextInt(max)+min).limit(l).toArray();
    }

    // alphebetize string
    public void sortStrings(String[] sList){
        Arrays.sort(sList, String::compareTo);
    }

    public List<String> sortStringsList00(List<String> sList){
        return sList.stream().sorted(String::compareTo).toList();
    }

    public List<String> sortStringsList01(List<String > sList){
        return sList.stream().sorted(String::compareTo).collect(Collectors.toList());
    }

    //contains letter
    public String[] filterStrings_notinplace(String[] sList, String letter){
        List<String> out = Arrays.stream(sList).filter(s->s.toUpperCase().contains(letter.toUpperCase())).toList();
        return out.toArray(new String[out.size()]);
    }

    //arrays are... immutable. So I can't really remove stuff in place can I
    public void filterStrings_inplace(List<String> sList, String letter){
        int i = 0;
        while(i<sList.size()){
            String s = sList.get(i);
            if (!s.toUpperCase().contains(letter.toUpperCase())){
                sList.remove(i);
            } else {
                i++;
            }
        }
    }



    public void sortStringsList02(List<String > sList){
        Collections.sort(sList, String::compareTo);
    }

    /*
    challenge I made up

    given list of integers, add every even entry to itself that many times
    1 2 3 2 5 4 -> 1 2 2 3 2 2 5 4 4 4 4
     */
    public void addEvensEvenTimes(List<Integer> iList){
        int i=0;
        while (i<iList.size()){
            int a = iList.get(i);
            System.out.println(a);
            if (a%2!=0){
                i++;
                continue;
            }
            for(int i1 = 0; i1<a-1;i1++){
                iList.add(i,a);
            }
            i=i+a;
        }


    }
    public void sbTest(){
        StringBuilder sb = new StringBuilder("Hello World");
        sb.insert(5, "!!!?");
        System.out.println(sb.reverse());
    }

    public void gymTest(){
        List<String> dayList = new ArrayList<>(List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
            for (String day: dayList){
                System.out.println(day);
                char d = day.charAt(0);
                switch (d){
                    case 'T':
                        System.out.println("GYM");
                        break;
                    case 'S':
                        System.out.println("GYM");
                        break;
                    default:
                        System.out.println("HOME");
                        break;
            }
        }
    }


    public void insertSort_retry_01(int[] arr){
        for(int i=0;i<arr.length;i++){
            int a = arr[i];
            for (int i0=i-1; i0>-1; i0--){
                if (a<arr[i0]){
                    int t = arr[i0];
                    arr[i0] = a;
                    arr[i0+1] = t;
                }
            }
        }

    }

    public int binarysearch_nonRecur(int[] arr, int target){
        int l = 0;
        int r = arr.length-1;
        int mid = (l+r)/2;
        while(l<=r){
            if (arr[mid]==target){
                return mid;
            } else if (arr[mid]>target){
                r=mid-1;
                mid = (l+r)/2;
            } else if (arr[mid]<target){
                l=mid+1;
                mid = (l+r)/2;
            }
        }
        return -1;
    }

    public int binarysearch_recur(int[] arr, int l, int r, int target){
        if(l>r){return -1;}
        int mid = (l+r)/2;
        if (arr[mid]==target){return mid;}
        if (arr[mid]>target){return binarysearch_recur(arr, l, mid-1, target);}
        if (arr[mid]<target){return binarysearch_recur(arr, mid+1,r, target);}
        return -1;
    }


    public static void main(String[] args){
        CodeSolutions c = new CodeSolutions();
        int[] l1 = {1,2,3,5,6};
        int[] l2 = {2,3,4};
        System.out.println(Arrays.toString(c.mergeArrays_00(l1, l2)));
        System.out.println(Arrays.toString(c.mergeArrays_01(l1, l2)));
        /*
        // doesn't work, list is immutable

        List<Integer> a1 = new ArrayList<>();
        List<Integer> a2 = new ArrayList<>();
        a1 = Arrays.stream(l1).boxed().toList();
        a2 = Arrays.stream(l2).boxed().toList();


        // so this DOES work
        List<Integer> a1 = new ArrayList<>();
        List<Integer> a2 = new ArrayList<>();
        a1 = Arrays.stream(l1).boxed().collect(Collectors.toList());
        a2 = Arrays.stream(l2).boxed().collect(Collectors.toList());

        //so does this but has some trouble getting the type for some reason
        List<Integer> a1 = new ArrayList<Integer>(List.of(1,2,3,5,6));
        List<Integer> a2 = new ArrayList<Integer>(List.of(2,3,4));
         */
        List<Integer> a1 = new ArrayList<Integer>(List.of(1,2,3,5,6));
        List<Integer> a2 = new ArrayList<Integer>(List.of(2,3,4));

        c.mergeArrayLists_00(a1,a2);
        System.out.println(a1);

        int[] l3 = {1,2,2,2,4,4,5};
        System.out.println(Arrays.toString(c.arrayOnce_00(l3)));
        System.out.println(Arrays.toString(c.arrayOnce_01(l3)));
        System.out.println(Arrays.toString(c.arrayOnce_02(l3)));
        String s1 = "  B    E es";
        System.out.println(c.deleteWhitespace_00(s1));
        System.out.println(c.deleteWhitespace_01(s1));



        int[] r1 = c.randomArray(10, 0, 100);
        c.bubblesort_00(r1);
        System.out.println(Arrays.toString(r1));

        int[] r2 = c.randomArray(10, 0, 100);
        c.linearsort_00(r2);
        System.out.println(Arrays.toString(r2));

        String[] x ={"Bees", "Apples"};
        c.sortStrings(x);
        System.out.println(Arrays.toString(x));
        List<String> x_o = new ArrayList<>();
        x_o.add("Bees");
        x_o.add("Apples");
        System.out.println(x_o.contains("Bees"));
        List<String> x2 = new ArrayList<>(x_o);
        System.out.println(c.sortStringsList00(x2));
        x2 = new ArrayList<>(x_o);
        System.out.println(c.sortStringsList01(x2));
        x2 = new ArrayList<>(x_o);
        c.sortStringsList02(x2);
        System.out.println(x2);
        //System.out.println(Boolean.valueOf("true"));
        String[] y = c.filterStrings_notinplace(x,"a");
        System.out.println(Arrays.toString(y));
        System.out.println(x2);
        c.filterStrings_inplace(x2, "a");
        System.out.println(x2);

        x =new String[]{"Bees", "Apples"};
        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(c.stringUpper_00(x)));

        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(c.stringUpper_01(x)));

        List<Integer> a = new ArrayList<Integer>(List.of(1, 2, 3, 2, 5, 4));
        c.addEvensEvenTimes(a);
        System.out.println(a);

        c.sbTest();
        c.gymTest();

        int[] i1 = new int[] {1,-1,1,-1,-1,1,1,-1};
        c.insertSort_retry_01(i1);
        System.out.println(Arrays.toString(i1));


    }






}
