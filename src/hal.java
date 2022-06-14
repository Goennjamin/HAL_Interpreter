import bufferpack.buffer;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;


public class hal extends Thread {
    private float[] mem;



    private float[] r;
    private float accu;
    private int pc = 0;
    //private float[] io;
    private buffer[] io;
    private Boolean[] io_mode;
    private String path;


    public hal(String path) {
        mem = new float[100];
        r = new float[15];
        accu = 0.0f;
        pc = 0;
        io = new buffer[10];
        io_mode = new Boolean[10];
        path = path;
    }


    /*public static void main(String[] args) throws Exception {


        float[] mem_main = new float[100];                //Die ganzen Register etc.
        float[] r_main = new float[15];
        float accu_main = 0.0f;
        int pc_main = 0;

        bufferpack.buffer[] Buffer = new bufferpack.buffer[2];
        Scanner scn = new Scanner(System.in);


        long start = System.nanoTime(); //Startzeit für die Runtime berechnung
        File file = new File(args[0]);  //Datei erstellen über argument liste
        BufferedReader br = new BufferedReader(new FileReader(file)); //Datei einlesen
        String str;
        int modus = 0;
        Vector<String[]> lines = new Vector<>(); //Vector für die Befehle
        while ((str = br.readLine()) != null) {
            lines.add(str.split(" ")); //Hier werden die 3 Zeilenelemente getrennt also 00 BEFEHL 0 und in den Vector geschrieben
        }

        System.out.print("Start debugging? 0=No , 1=Yes\nNumber: "); //Debugging abfrage
        modus = scn.nextInt();

        if(Integer.parseInt(lines.firstElement()[0])!=pc){ //Check das die Zeilennummer gleich dem PC ist
            throw new Exception("syntax-error at line " + pc + ": wrong line number.");
        }

        if(lines.firstElement()[1].equals("START")){ //Check das die erste Zeile ein START ist
            if (modus == 1) System.out.println(lines.firstElement()[0] + " " + lines.firstElement()[1] + ": start");
        }
        else{
            throw new Exception("semantic-error at line " + pc + ": program not started");
        }

        pc++; //PC wird vor dem schleifendurchlauf inkrementiert weil start schon geprüft wurde
        while (pc < lines.size()-1) {
            int l0 = Integer.parseInt(lines.elementAt(pc)[0]); // Zeilennummer
            String l1 = lines.elementAt(pc)[1];                // Befehl
            int l2 = Integer.parseInt(lines.elementAt(pc)[2]); // Operand


            if (Integer.parseInt(lines.elementAt(pc)[0]) != pc) {
                if (modus == 1) System.out.println(l0 + " " + l1 + " " + l2); //Check das die Zeilennummer gleich dem PC ist
                throw new Exception("syntax-error at line " + pc + ": wrong line number.");
            }

            switch (lines.elementAt(pc)[1]) {
                case "START":
                    if (modus == 1) System.out.println(l0 + " " + l1);
                    throw new Exception("semantic-error at line " + pc + ": forbidden use of keyword \"START\".");
                case "STOP":
                    if (modus == 1) System.out.println(l0 + " " + l1);
                    throw new Exception("syntax-error at line " + pc + ": forbidden use of keyword \"STOP\".");
                case "OUT":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": io["+l2+"](" + io[l2] + ")=accu(" + accu + ")");
                    io[l2] = accu;
                    System.out.println(io[l2]);
                    break;
                case "IN":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + accu + ")=io["+l2+"](" + io[l2] + ")");
                    io[l2] = scn.nextFloat();
                    accu = io[l2];
                    break;
                case "LOAD":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + accu + ")=r[" + l2 + "](" + r[l2] + ")");
                    accu = r[l2];
                    break;
                case "LOADNUM":
                    if (modus == 1) System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + accu + ")=" + l2);
                    accu = l2;
                    break;
                case "STORE":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": r[" + l2 + "](" + r[l2] + ")=accu(" + accu + ")");
                    r[l2] = accu;
                    break;
                case "JUMPNEG":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + accu + ")<0 -> pc(" + pc + ")=" + l2);
                    if (accu < 0) pc = l2 - 1;
                    break;
                case "JUMPPOS":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + accu + ")>0 -> pc(" + pc + ")=" + l2);
                    if (accu > 0) pc = l2 - 1;
                    break;
                case "JUMPNULL":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + accu + ")=0 -> pc(" + pc + ")=" + l2);
                    if (accu == 0) pc = l2 - 1;
                    break;
                case "JUMP":
                    if (modus == 1) System.out.println(l0 + " " + l1 + " " + l2 + ": pc(" + pc + ")=" + l2);
                    pc = l2-1;
                    break;
                case "ADD":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + (accu + r[l2]) + ")=accu(" + accu + ")+r[" + l2 + "](" + r[l2] + ")");
                    accu += r[l2];
                    break;
                case "ADDNUM":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + (accu + l2) + ")=accu(" + accu + ")+" + l2);
                    accu += l2;
                    break;
                case "SUB":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + (accu - r[l2]) + ")=accu(" + accu + ")-r[" + l2 + "](" + r[l2] + ")");
                    accu -= r[l2];
                    break;
                case "MUL":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + (accu * r[l2]) + ")=accu(" + accu + ")*r[" + l2 + "](" + r[l2] + ")");
                    accu *= r[l2];
                    break;
                case "DIV":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + (accu / r[l2]) + ")=accu(" + accu + ")/r[" + l2 + "](" + r[l2] + ")");
                    accu /= r[l2];
                    break;
                case "SUBNUM":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + (accu - l2) + ")=accu(" + accu + ")-" + l2);
                    accu -= l2;
                    break;
                case "MULNUM":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + (accu * l2) + ")=accu(" + accu + ")*" + l2);
                    accu *= l2;
                    break;
                case "DIVNUM":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + (accu / l2) + ")=accu(" + accu + ")/" + l2);
                    accu /= l2;
                    break;
                case "LOADIND":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + accu + ")=mem[" + (int) r[l2] + "](" + mem[(int) r[l2]]+")");
                    accu = mem[(int) r[l2]];
                    break;
                case "STOREIND":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": mem[" + (int) r[l2] + "](" + mem[(int) r[l2]]+")=accu("+accu+")");
                    mem[(int) r[l2]] = accu;
                    break;
                default:
                    if (modus == 1) System.out.println(l0 + " " + l1 + " " + l2);
                    throw new Exception("syntax-error at line " + pc + ": command not found.");
            }
            pc++;
        }

        if(Integer.parseInt(lines.lastElement()[0])!=pc){//Check das die Zeilennummer gleich dem PC ist
            throw new Exception("syntax-error at line " + pc + ": wrong line number.");
        }

        if(lines.lastElement()[1].equals("STOP")){ //Check das letzte Zeile STOPP ist
            if (modus == 1) System.out.println(lines.lastElement()[0] + " " + lines.lastElement()[1] + ": stop");
        }
        else{
            throw new Exception("semantic-error at line " + pc + ": program not ending");
        }

        long end = System.nanoTime();//Endpunkt für Runtime berechnung
        long duration = (end-start)/1000000000; //Die Rechnung für die Runtime
        System.out.println("Laufzeit: "+duration+"s");
        return;
    }*/

    @Override
    public void run() {

        try{

            Scanner scn = new Scanner(System.in);


            long start = System.nanoTime(); //Startzeit für die Runtime berechnung
            File file = new File(path);  //Datei erstellen über argument liste
            BufferedReader br = new BufferedReader(new FileReader(file)); //Datei einlesen
            String str;
            Vector<String[]> lines = new Vector<>(); //Vector für die Befehle
            while ((str = br.readLine()) != null) {
                lines.add(str.split(" ")); //Hier werden die 3 Zeilenelemente getrennt also 00 BEFEHL 0 und in den Vector geschrieben
            }

            System.out.print("Start debugging? 0=No , 1=Yes\nNumber: "); //Debugging abfrage


            if(Integer.parseInt(lines.firstElement()[0])!=pc){ //Check das die Zeilennummer gleich dem PC ist
                throw new Exception("syntax-error at line " + pc + ": wrong line number.");
            }

            if(lines.firstElement()[1].equals("START")){ //Check das die erste Zeile ein START ist

            }
            else{
                throw new Exception("semantic-error at line " + pc + ": program not started");
            }

            pc++; //PC wird vor dem schleifendurchlauf inkrementiert weil start schon geprüft wurde
            while (pc < lines.size()-1) {
                int l0 = Integer.parseInt(lines.elementAt(pc)[0]); // Zeilennummer
                String l1 = lines.elementAt(pc)[1];                // Befehl
                int l2 = Integer.parseInt(lines.elementAt(pc)[2]); // Operand


                if (Integer.parseInt(lines.elementAt(pc)[0]) != pc) {
                    throw new Exception("syntax-error at line " + pc + ": wrong line number.");
                }

                switch (lines.elementAt(pc)[1]) {
                    case "START":

                        throw new Exception("semantic-error at line " + pc + ": forbidden use of keyword \"START\".");
                    case "STOP":

                        throw new Exception("syntax-error at line " + pc + ": forbidden use of keyword \"STOP\".");
                    case "OUT":
                        io[l2] = accu;
                        System.out.println(io[l2]);
                        break;
                    case "IN":
                        io[l2] = scn.nextFloat();
                        accu = io[l2];
                        break;
                    case "LOAD":
                        accu = r[l2];
                        break;
                    case "LOADNUM":
                        accu = l2;
                        break;
                    case "STORE":
                        r[l2] = accu;
                        break;
                    case "JUMPNEG":
                        if (accu < 0) pc = l2 - 1;
                        break;
                    case "JUMPPOS":
                        if (accu > 0) pc = l2 - 1;
                        break;
                    case "JUMPNULL":
                        if (accu == 0) pc = l2 - 1;
                        break;
                    case "JUMP":
                        pc = l2-1;
                        break;
                    case "ADD":
                        accu += r[l2];
                        break;
                    case "ADDNUM":
                        accu += l2;
                        break;
                    case "SUB":
                        accu -= r[l2];
                        break;
                    case "MUL":
                        accu *= r[l2];
                        break;
                    case "DIV":
                        accu /= r[l2];
                        break;
                    case "SUBNUM":
                        accu -= l2;
                        break;
                    case "MULNUM":
                        accu *= l2;
                        break;
                    case "DIVNUM":
                        accu /= l2;
                        break;
                    case "LOADIND":
                        accu = mem[(int) r[l2]];
                        break;
                    case "STOREIND":
                        mem[(int) r[l2]] = accu;
                        break;
                    default:
                        throw new Exception("syntax-error at line " + pc + ": command not found.");
                }
                pc++;
            }

            if(Integer.parseInt(lines.lastElement()[0])!=pc){//Check das die Zeilennummer gleich dem PC ist
                throw new Exception("syntax-error at line " + pc + ": wrong line number.");
            }

            if(lines.lastElement()[1].equals("STOP")){ //Check das letzte Zeile STOPP ist
            }
            else{
                throw new Exception("semantic-error at line " + pc + ": program not ending");
            }

            long end = System.nanoTime();//Endpunkt für Runtime berechnung
            long duration = (end-start)/1000000000; //Die Rechnung für die Runtime
            System.out.println("Laufzeit: "+duration+"s");
            return;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}

