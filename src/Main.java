import java.io.*;
import java.util.ArrayList;


public class Main {

    public boolean START(){
        return true;
    }

    public void STOP(){

    }

    public void OUT(){

    }

    public void IN(){

    }

    public void LOAD(){

    }

    public void LOADNUM(){

    }

    public void STORE(){

    }

    public void JUMPNEG(){

    }

    public void JUMPPOS(){

    }

    public void JUMPNULL(){

    }

    public void JUMP(){

    }

    public void ADD(){

    }

    public void ADDNUM(){

    }

    public void SUB(){

    }

    public void MUL(){

    }

    public void SUBNUM(){

    }

    public void MULNUM(){

    }

    public void DIVNUM(){

    }

    public void LOADIND(){

    }

    public void STOREIND(){

    }

    public static void main(String[] args) throws Exception {

        File file = new File("/home/leonw/Dokumente/HAL_Programm.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        ArrayList<String> Befehle = new ArrayList<>();


        while((st = br.readLine()) != null){

            Befehle.add(st);
        }

        int r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,Accu,PC = 0;


    }
}




