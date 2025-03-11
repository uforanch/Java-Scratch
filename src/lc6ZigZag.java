import java.util.Arrays;

public class lc6ZigZag {
    //time limit exceeded. ugh
    public String convert(String s, int numRows){
        if (numRows<=1){return s;}
        char[] s_c = s.toCharArray();
        int r = 0;
        int r_p = 1;
        int c = 0;
        int c_p = 0;
        char[][] grid_c = new char[numRows][s_c.length];
        char[] ret_c = new char[s_c.length];
        for(int i=0; i<numRows; i++){
            Arrays.fill(grid_c[i], ' ');
        }
        for(int i=0; i<s_c.length;i++){
            System.out.println(String.format("%d %d", r,c));
            grid_c[r][c]=s_c[i];
            r+=r_p;
            c+=c_p;
            if (r>=numRows){
                r-=2;
                c+=1;
                r_p=-1;
                c_p=1;
            } else if (r<0){
                r=1;
                c-=1;
                r_p=1;
                c_p=0;
            }
        }
        System.out.println("--------");
        int i = 0;
        for(r=0;r<numRows;r++){
        for(c =0; c< s_c.length; c++){

                System.out.println(String.format("%d %d", r,c));
                if (grid_c[r][c]!=' '){
                    ret_c[i]=grid_c[r][c];
                    i++;
                }
            }
        }

        return String.valueOf(ret_c);
    }

    public String convert00(String s, int numRows){
        char[] s_c = s.toCharArray();
        int rowLength = s_c.length/numRows+1;
        char[][] mid_c = new char[numRows][rowLength];
        char[] ret_c = new char[s_c.length];
        int rc = 1;
        int r=0;
        int c=0;
        for(int i =0; i<s_c.length;i++){
            mid_c[c][r] = s_c[i];
            r+=rc;
            if ((rc>0)&&(r>=rowLength)){
                r=rowLength-1;
                rc=-1;
                c+=1;
            } else if ((rc<0)&&(r<0)) {
                r = 0;
                rc = 1;
                c += 1;
            }
        }
        int i=0;
        outer:
        for(r=0;r<rowLength;r++){
            for(c=0; c<numRows;c++){
                ret_c[i]=mid_c[c][r];
                i+=1;
                if (i>=s_c.length){break outer;}
            }
        }
        return String.valueOf(ret_c);
    }

    public void test1(){
        String s = "PAYPALISHIRING";
        String s2=convert(s,3);
        String s3="PAHNAPLSIIGYIR";
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s2.equals(s3));
    }

    public static void main(String args[]){
        lc6ZigZag v = new lc6ZigZag();
        v.test1();

    }
}
