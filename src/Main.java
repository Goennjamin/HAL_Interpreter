import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;



public class Main {

    static Scanner scn = new Scanner(System.in);

    static boolean debug = false;

    static double[] io = new double[2];

    static double accu,pc,e_a,operand = 0;

    static double[] register   = new double[16];

    static double[] memory     = new double[100];


    static public void START(){


    }

    static public void STOP(){


    }

    static public void OUT(){

        io[1]=accu;
        System.out.println(io[1]);
    }

    static public void IN(){

        io[0] = scn.nextDouble();

        accu = io[0];

    }

    static public void LOAD(){

        accu = register[(int) operand];

    }

    static public void LOADNUM(){

        accu = operand;

    }

    static public void STORE(){

        register[(int) operand] = accu;

    }

    static public void JUMPNEG(){

        if(accu<0){
            pc = operand;
        }

    }

    static public void JUMPPOS(){

        if(accu>0){
            pc = operand;
        }

    }

    static public void JUMPNULL(){

        if(accu==0){
            pc = operand;
        }

    }

    static public void JUMP(){

        pc = operand;

    }

    static public void ADD(){

        accu = accu + register[(int) operand];

    }

    static public void ADDNUM(){

        accu = accu + operand;

    }

    static public void SUB(){

        accu = accu - register[(int) operand];

    }

    static public void MUL(){

        accu = accu * register[(int) operand];

    }

    static public void DIV(){

        accu = accu / register[(int) operand];

    }

    static public void SUBNUM(){

        accu = accu - operand;

    }

    static public void MULNUM(){

        accu = accu * operand;

    }

    static public void DIVNUM(){

        accu = accu / operand;

    }

    static public void LOADIND(){

        accu = memory[(int) register[(int) operand]];

    }

    static public void STOREIND(){

        memory[(int) register[(int) operand]] = accu;

    }

    public static void main(String[] args) throws Exception {

        long startTime = System.nanoTime();


        File file = new File("/home/leonw/Dokumente/HAL_Programm.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        ArrayList<String[]> Befehle = new ArrayList<>();


        while((st = br.readLine()) != null){

            Befehle.add(st.split(" "));
        }

        System.out.println("Debug? (y/n)");

        String selection = scn.next();

        if(selection == "y"){
            debug = true;
        }


        for(int i = 0; i < Befehle.size(); i++){


            if(Objects.equals(Befehle.get((int) pc)[1], "START")){
                START();

            }

            if(Objects.equals(Befehle.get((int) pc)[1], "STOP")){
                STOP();
                break;
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "OUT")){

                if(debug){

                }
                OUT();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "IN")){

                if(debug){

                }

                IN();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "LOAD")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                LOAD();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "LOADNUM")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                LOADNUM();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "STORE")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                STORE();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "JUMPNEG")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                JUMPNEG();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "JUMPPOS")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                JUMPPOS();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "JUMPNULL")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                JUMPNULL();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "JUMP")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                JUMP();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "ADD")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                ADD();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "ADDNUM")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                ADDNUM();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "SUB")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                SUB();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "SUBNUM")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                SUBNUM();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "MUL")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                MUL();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "MULNUM")){

                if(debug){

                }operand = Double.parseDouble(Befehle.get(i)[2]);
                MULNUM();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "DIV")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                DIVNUM();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "LOADIND")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                LOADIND();

                if(debug){

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "LOADNUM")){

                if(debug){

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                LOADNUM();

                if(debug){

                }
            }

            pc++;

        }  //TODO: Debug ausführlich machen



        long endTime = System.nanoTime();
        long duration = ((endTime - startTime) / 1000000) / 1000;
        System.out.println("runtime: " + duration);
        
    }


}




