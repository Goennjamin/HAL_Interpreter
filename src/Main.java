import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;



public class Main {

    static Scanner scn = new Scanner(System.in);

    static boolean debug = false;

    static double accu,pc,e_a,operand = 0;

    static double[] register   = new double[16];
    static double[] memory     = new double[100];
    static double[] io = new double[2];

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

        if(Objects.equals(selection, "y")){
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

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("E/A 1: " + io[1]);

                }
                OUT();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("E/A 1: " + io[1]);
                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "IN")){

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("E/A 0: " + io[0]);

                }

                IN();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("E/A 0: " + io[0]);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "LOAD")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);

                }

                LOAD();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "LOADNUM")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);

                }

                LOADNUM();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "STORE")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);

                }

                STORE();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "JUMPNEG")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("PC: " + pc);
                    System.out.println("Operand: " + operand);

                }

                JUMPNEG();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("PC: " + pc);
                    System.out.println("Operand: " + operand);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "JUMPPOS")){

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("PC: " + pc);
                    System.out.println("Operand: " + operand);

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                JUMPPOS();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("PC: " + pc);
                    System.out.println("Operand: " + operand);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "JUMPNULL")){

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("PC: " + pc);
                    System.out.println("Operand: " + operand);

                }

                operand = Double.parseDouble(Befehle.get(i)[2]);
                JUMPNULL();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("PC: " + pc);
                    System.out.println("Operand: " + operand);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "JUMP")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("PC: " + pc);
                    System.out.println("Operand: " + operand);

                }


                JUMP();

                if(debug){

                    System.out.println("After:");
                    System.out.println("PC: " + pc);
                    System.out.println("Operand: " + operand);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "ADD")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);

                }


                ADD();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "ADDNUM")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Operand: " + operand);

                }


                ADDNUM();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Operand: " + operand);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "SUB")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);

                }


                SUB();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "SUBNUM")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Operand: " + operand);

                }


                SUBNUM();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Operand: " + operand);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "MUL")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);

                }


                MUL();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "MULNUM")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Operand: " + operand);

                }

                MULNUM();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Operand: " + operand);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "DIV")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);

                }


                DIV();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "DIVNUM")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Operand: " + operand);

                }

                DIVNUM();

                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Operand: " + operand);

            }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "LOADIND")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);
                    System.out.println("Memory m: " + memory[(int) register[(int) operand]]);

                }


                LOADIND();


                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);
                    System.out.println("Memory m: " + memory[(int) register[(int) operand]]);

                }
            }

            if(Objects.equals(Befehle.get((int) pc)[1], "STOREIND")){

                operand = Double.parseDouble(Befehle.get(i)[2]);

                if(debug){

                    System.out.println("Before:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);
                    System.out.println("Memory m: " + memory[(int) register[(int) operand]]);

                }


                STOREIND();


                if(debug){

                    System.out.println("After:");
                    System.out.println("Accu: " + accu);
                    System.out.println("Register r" + (int) operand + ": "+ register[(int) operand]);
                    System.out.println("Memory m: " + memory[(int) register[(int) operand]]);

                }
            }

            if(debug){


                System.out.println("For next line write n");
                String next = scn.next();


            }


            pc++;

        }



        long endTime = System.nanoTime();
        long duration = ((endTime - startTime) / 1000000) / 1000;
        System.out.println("Runtime: " + duration + " Sekunden");
        
    }


}




