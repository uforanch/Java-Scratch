import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CTCquestions {
    final String[] numWords = {
            "Zero",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen"};
    final String[] tenWords = {
            "",
            "",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety"};
    static class Country{
        private String name;
        private String continent;
        private int pop;
        Country(String name, String continent, int pop){
            this.name=name;
            this.continent = continent;
            this.pop=pop;
        }
        public int getPop(){
            return pop;
        }
        public String getContinent(){
            return continent;
        }
        public String getName(){
            return name;
        }
    }

    public void ctcLambdaExpCountry(){
        List<Country> cl = new ArrayList<>();
        cl.add(new Country("X1", "X",1));
        cl.add(new Country("X2", "X",2));
        cl.add(new Country("Y1", "Y",3));
        cl.add(new Country("Y2", "Y",4));
        BiFunction<List<Country>, String, Integer> f = (cl_, c) -> cl_.stream()
                .filter((c_)->(c_.getContinent().equals(c)))
                .map((c_)->(c_.getPop()))
                .mapToInt((x)->x)
                .sum();//can use sum if maptoint first
        //ok, so don't use {}
            //alt .collect.(Collectors.summingInt(Integer::intValue))
        System.out.println( f.apply(cl,"Y"));
    }
    /*
    what we've learned here is that java lambdas are EXTREMELY limited and in most cases
    you gotta just deal with class methods

    there's pretty much no way to do functional java
     */
    public List<Integer> getRandomSubset(List<Integer> iList){
        Random random = new Random();
        return iList.stream().filter((x)->random.nextBoolean()).collect(Collectors.toList());
    }

    private String firstDigitWord(String s){
        return numWords[Integer.valueOf(s.substring(0,1))];
    }

    public String numberEnglish(int x){
        String x_s = Integer.toString(x);
        int l = x_s.length();
        if (l>=12){
            return String.join(" ",
                    numberEnglish(Integer.valueOf(x_s.substring(0,l-11))),
                    "Trillion,",
                    numberEnglish(Integer.valueOf(x_s.substring(l-11))));

        } else if (l>=9){
            return String.join(" ",
                    numberEnglish(Integer.valueOf(x_s.substring(0,l-8))),
                    "Billion,",
                    numberEnglish(Integer.valueOf(x_s.substring(l-8))));
        } if (l>=6){
            return String.join(" ",
                    numberEnglish(Integer.valueOf(x_s.substring(0,l-5))),
                    "Million,",
                    numberEnglish(Integer.valueOf(x_s.substring(l-5))));
        } if (l>3){
            return String.join(" ",
                    numberEnglish(Integer.valueOf(x_s.substring(0,l-3))),
                    "Thousand,",
                    numberEnglish(Integer.valueOf(x_s.substring(l-3))));
        }  if (l==3){
            return String.join(" ",
                    numberEnglish(Integer.valueOf(x_s.substring(0,1))),
                    "Hundred,",
                    numberEnglish(Integer.valueOf(x_s.substring(1))));
        } if (x_s.length()<=2) {
            if (x<20){
                return numWords[x];
            } else if (x%10==0){
                return tenWords[(int) x/10];
            } else {
                return String.join(" ", tenWords[(int) (x-x%10)/10], numWords[x%10]);
            }
        }

        return " ";
    }

    public void WorldePrint(String word, String guess){
        if (word.length() != guess.length()){
            System.out.println("need equal lengths");
            return;
        }
        int green = 0;
        int yellow = 0;
        Map<Character,Integer> hm = new HashMap<>();
        for(int i=0;i<word.length();i++){
            char c = word.toUpperCase().charAt(i);
            hm.put(c, hm.getOrDefault(c,0) + 1);
        }
        //need one pass just for greens
        for(int i=0;i<guess.length(); i++){
            char c = word.toUpperCase().charAt(i);
            char c_g = guess.toUpperCase().charAt(i);
            if(c==c_g){
                green++;
                hm.put(c, hm.get(c)-1);
            }
        }
        for(int i=0;i<guess.length(); i++){
            char c_g = guess.toUpperCase().charAt(i);
            int hm_c = hm.getOrDefault(c_g,0);

            if(hm_c>0){
                yellow++;
                hm.put(c_g, hm_c-1);
            }
        }
        System.out.println(String.format("%d green %d yellow", green, yellow));
    }

    public static void main(String[] args){
        CTCquestions c = new CTCquestions();
        c.ctcLambdaExpCountry();
        System.out.println(c.getRandomSubset(List.of(1,2,3,4,5)));

        System.out.println(String.valueOf(1000).substring(0,1));
        System.out.println(String.join(" ", ".", "."));
        System.out.println(String.join(" ", new String[]{".", "."}));
        System.out.println(c.numberEnglish(1923928383));
        System.out.println(c.numberEnglish(2413));
        c.WorldePrint("axax","xaxx");

    }



}
