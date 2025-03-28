public class classTests {
    private static class Thing{
        private int i =0;
        public Thing(){}
        public Thing(int i){
            this.i=i;
        }
        public Thing(Thing t){
            i = t.i;
        }
        private Thing(Thing t1, Thing t2){
            i = t1.geti() + t2.geti();
        }
        private int geti(){
            return i;
        }
        public Thing addThings(Thing t1, Thing t2){
            return new Thing(t1,t2);
        }
        public void speak(){
            System.out.println(i);
        }
    }

    public static void main(String[] args){
        Thing t1 = new Thing();
        Thing t2 = new Thing(2);
        Thing t3 = new Thing(t2);
        Thing t4 = t1.addThings(t2,t3);
        t1.speak(); t2.speak(); t3.speak(); t4.speak();
    }
}
