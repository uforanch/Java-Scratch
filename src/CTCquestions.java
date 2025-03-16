import java.util.Random;

public class CTCquestions {
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

    
}
