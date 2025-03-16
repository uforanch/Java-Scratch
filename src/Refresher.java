

/*
NOT IN THE BELOW CODE

(int val)->val+1 is how you do lambda

arraylist has get() and size(), not [] and length

= new int[] {1,2,3}
= new ArrayList<>(List.of(1,2,3))

hashset retainAll(collection) is intersection, removeAll(collection) difference
hashmap get put for keys, constainsKey

StringBuilder toString, append, etc (start blank add this)

Collections.sort(arrayList), Collections.reverse(arrayList)


Arrays.sort(arr) Arrays.toString(arr)
sort(arr, int from, int to)
Arrays.sort(T[] a, Comparator<? super T> c)
Arrays.binarySearch(int[] a, int key)

sooooo no way to reverse non manually


The collect(Collectors.toList()) method was added to Java in Java 8 as a part of the Stream API.
However, a more convenient method, Stream.toList(), was introduced in Java 16. This method provides a more concise way to collect the elements of a stream into a list.

System.out.println("Bees".compareTo("Apples")) = 1, so proper compare function x.compareTo(y) would be x-y
meaning comparator x,y should be x>y



    public void printArray(List E, String join){
        System.out.println(E.stream().map(Object::toString).collect(Collectors.joining(join)));
    }


 */

/*
    @Controller
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Pie> getPieList(){ return pieService.getPieList(); }


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {
    @ManagerOnly
    @PatchMapping("{userId}/delete")
    public  ResponseEntity<String>  deleteUser(@PathVariable String userId){
        OutgoingUser valid_user = new OutgoingUser(userService.getUserByShortId(userId));
        userService.deleteUser(valid_user);
        return ResponseEntity.accepted().body("successfully deleted");
    }


         testList.sort(Comparator.comparing(Point::getX).reversed());

    REPOSITIORY NEEDS ID
    public interface ReimbursementDAO extends JpaRepository<Reimbursement,Integer>
}



 */


import java.util.*;
import java.util.stream.Collectors;


public class Refresher {

    static public class Tuple<F, S> {
        final F f;
        final S s;
        public Tuple(F f, S s) {
             this.f = f;
             this.s = s;
        }

        @Override
        public int hashCode() {
            int fH = f.hashCode();
            int sH = s.hashCode();
            var fh_rotated = Integer.rotateLeft(fH, Integer.bitCount(sH));
            return sH | fh_rotated;

            /*
            bit rotate fH
             */
            //return super.hashCode();
        }
    }


    //arraylist to array
    public void ex1(){
        //https://stackoverflow.com/questions/718554/how-to-convert-an-arraylist-containing-integers-to-primitive-int-array
        List<Integer> x =  new ArrayList<Integer>();
        //no initializer is allowed here for starting from an intp[
        x.add(1);
        x.add(2);
        x.add(3);
        Object[] y1 = x.toArray();//why
        int[] y2 = x.stream().mapToInt(i -> i).toArray();
        //remove null
        int[] y3 = x.stream().filter(i -> i != null).mapToInt(i -> i).toArray();

        //no streams... note
        //array
        int[] ret = new int[x.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = x.get(i).intValue();
        }
        System.out.println("list to array without error");

    }

    //list to array
    public void ex2(){
        int[] ints = {1,2,3};
        var list1 = Arrays.stream(ints).boxed().collect(Collectors.toList());
        var list2 = Arrays.stream(ints).boxed().toList();//found out later this is immutable
        System.out.println(list1.getClass().getName());
        System.out.println(list2.getClass().getName());
        list1.set(0,2);
        //list2.set(0,2);//will error
        System.out.println("array to list without error");
    }

    //string join example, string to int example
    public void ex3(){
        //string dot join ONLY works with arrays of strings
        System.out.println(String.join(", ", new String[]{"5", "6"}));

        int i = (int) Integer.valueOf("7");
    }

    public void printIntArray(int[] arr){
        System.out.println(Arrays.toString(arr));
    }

    public void printArray(List E, String join){
        System.out.println(E.stream().map(Object::toString).collect(Collectors.joining(join)));
    }

    public static class Point{
        public int x;
        public int y;
        Point(int x,int y){this.x=x; this.y=y;}
        public int getX(){return x;}
        public int getY(){return y;}
        @Override
        public String toString(){
            return String.format("(%d, %d)", x, y);
        }
        @Override
        public boolean equals(Object other){
            if (this==other){return true;}
            if (other == null || getClass() != other.getClass()){
                return false;
            }
            return ((Point) other).x==x && ((Point) other).y==y;

        }
        @Override // NEED a hashcode in order to have sorting
        public int hashCode(){
            //https://stackoverflow.com/questions/9135759/java-hashcode-for-a-point-class
            return 31*x+y;
        }
    }

    public List<Point> getPoints(int N){
        Random rand = new Random();
        List<Point> ret = new ArrayList<>();
        for (int i=0; i<N;i++){
            ret.add(new Point(rand.nextInt(100), rand.nextInt(100)));
        }
        return ret;
    }



    public void ex4(){

        List<Point> testList = getPoints(10);
        //sorts descending
        Collections.sort(testList, new Comparator<Point>() {
            @Override
            public int compare(Point lhs, Point rhs) {
                // -1 - greater than, 1 - less than, 0 - equal, all inversed for descending
                return lhs.getX() > rhs.getX() ? -1 : (lhs.getX() < rhs.getX()) ? 1 : 0;
            }
        });
        printArray(testList, ", ");


        testList = getPoints(10);

        //ClassName::getFieldName
        //means GETTER in particular
        testList.sort(Comparator.comparing(Point::getX));
        printArray(testList, ", ");

        testList = getPoints(10);
         testList.sort(Comparator.comparing(Point::getX).reversed());
        printArray(testList, ", ");

        testList = getPoints(10);
 testList.stream().sorted(Comparator.comparing(Point::getX).reversed()).collect(Collectors.toList());
        printArray(testList, ", ");


    }

    //remember match case, hash maps, etc
    public String encode00(String s){
        StringBuilder sb = new StringBuilder();
        for(char c : s.toUpperCase().toCharArray()){
            switch (c) {
                case 'A' -> sb.append('1');
                case 'B' -> sb.append('2');
                case 'C' -> sb.append('3');
                case 'D' -> sb.append('4');
                case 'E' -> sb.append('5');
                case 'F' -> sb.append('6');
                case 'G' -> sb.append('7');
                default -> sb.append('?');
            }
        }

        return sb.toString();
    }
    public String encode01(String s){
        char[] ret_c = new char[s.length()];
        Map<Character, Character> hm = new HashMap<>();
        hm.put('A', '1');
        hm.put('B', '2');
        hm.put('C', '3');
        hm.put('D', '4');
        hm.put('E', '5');
        hm.put('F', '6');
        hm.put('G', '7');
        System.out.println(hm.get('H')); //null, not an exception. cool.
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if (hm.containsKey(c)){
                ret_c[i] = hm.get(c);
            } else {
                ret_c[i] = '?';
            }
        }
        return String.valueOf(ret_c);
    }

    public void ex5(){
        String s = "FACES!";
        System.out.println(encode00(s));
        System.out.println(encode01(s));

    }
    public void ex6(){
        Set<Integer> hs1 = new HashSet<>();
        Set<Integer> hs2 = new HashSet<>();
        hs1.add(1); hs1.add(2); hs1.add(4);
        hs2.add(2); hs2.add(4); hs2.add(5);
        hs1.retainAll(hs2); // bool. true if set changed.
        printArray(new ArrayList<>(hs1), " - ");
        Set<Point> hs3 = new HashSet<>();
        Set<Point> hs4 = new HashSet<>();
        hs3.add(new Point(2,1)); hs4.add(new Point(2,1));
        hs3.add(new Point(1,32));
        hs3.removeAll(hs4);
        printArray(new ArrayList<>(hs3), " - ");

    }

    public void ex7(){
        String[][] deepArray = new String[][] {{"John", "Mary"}, {"Alice", "Bob"}};
// Gives undesired output:
        System.out.println(Arrays.toString(deepArray));
// Gives the desired output:
        System.out.println(Arrays.deepToString(deepArray));
    }

    public void ex8(){
        int[] l1 = {1,2,3,5,6};
        List<Integer> a1 = Arrays.stream(l1).boxed().toList();
        List<Integer> a2 = Arrays.stream(l1).boxed().collect(Collectors.toList());
        List<Integer> a3 = new ArrayList<Integer>(List.of(1,2,3,5,6));
        try {
            a1.add(1, 1);
        } catch(Exception ex) {
            System.out.println("error");
            System.out.println(ex.getMessage());//... null?
            System.out.println(ex);
        }
        a2.add(1,1);
        a3.add(1,1);
    }

    public int c00(Integer i1, Integer i2){
        if (i1-i2==0){
            return 0;
        } else if (i1-i2>0){
            return 1;
        } else {
            return -1;
        }
    }

    public void ex9(){
        List<Integer> l = new ArrayList<>(List.of(0,-1,1));//list of is ALSO immutible
        System.out.println(List.of(1,2,3).getClass().getName());
        l.sort(this::c00);
        System.out.println(l);
        System.out.println(l.stream().max(this::c00));

    }




    public void test_00(){
        System.out.println(new ArrayList<Integer>()==null);
    }
    class TestItClass00 implements Iterable<Integer> {
        private int[] data;
        private int size;

        /*
        ai gened but then adapted
        Was wondering if I'd have to make a spliterator which I don't get
         */

        public TestItClass00() {
            this.data = new int[]{1, 2, 3, 4, 5};
            this.size = 5;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new TestIt();
        }


        //had forgotten Implements for Interfaces
        private class TestIt implements Iterator<Integer> {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[currentIndex++];
            }
        }
    }

    public void ex10(){
        //iterators review


        Set<Integer> s = new HashSet<>(List.of(-1,1,3));
        Set<Integer> s1 = new HashSet<>(List.of(3,-1,1));

        /*
        Collections are ITERABLE, which means they have the iterator method,
        which generates an iterable

        the iterable has hasnext and next methods


         */
        System.out.println(s.iterator().next());
        System.out.println(s.iterator().next());
        System.out.println(s.iterator().next());
        System.out.println(s1.iterator().next());
        /*
        so to get one item out of a set, you need to get the iterator out of it and get the next out of it
         */
        for(int i: new TestItClass00()){
            System.out.println(i);
        }

    }

    public static void main(String args[]){
        Refresher r = new Refresher();
        System.out.println("---ex1---");
        r.ex1();
        System.out.println("---ex2---");
        r.ex2();
        System.out.println("---ex3---");
        r.ex3();
        System.out.println("---ex4---");
        r.ex4();
        System.out.println("---ex5---");
        r.ex5();
        System.out.println("---ex6---");
        r.ex6();
        System.out.println("---ex7---");
        r.ex7();
        System.out.println("---ex8---");
        r.ex8();
        System.out.println("---ex9---");
        r.ex9();

        System.out.println("---test00---");
        r.test_00();

        System.out.println("---ex10---");
        r.ex10();


    }




}
