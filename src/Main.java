import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    private static float[] mem = new float[100];
    private static float[] r = new float[15];
    private static float accu = 0.0f;
    private static int pc = 0;
    private static float[] io = new float[2];
    private static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        long start = System.nanoTime();
        File file = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        int modus = 0;
        Vector<String[]> lines = new Vector<>();
        while ((str = br.readLine()) != null) {
            lines.add(str.split(" "));
        }

        System.out.print("Start debugging? 0=No , 1=Yes\nNumber: ");
        modus = scn.nextInt();

        if(Integer.parseInt(lines.firstElement()[0])!=pc){
            throw new Exception("syntax-error at line " + pc + ": wrong line number.");
        }

        if(lines.firstElement()[1].equals("START")){
            if (modus == 1) System.out.println(lines.firstElement()[0] + " " + lines.firstElement()[1] + ": start");
        }
        else{
            throw new Exception("semantic-error at line " + pc + ": program not started");
        }

        pc++;
        while (pc < lines.size()-1) {
            int l0 = Integer.parseInt(lines.elementAt(pc)[0]);
            String l1 = lines.elementAt(pc)[1];
            int l2 = Integer.parseInt(lines.elementAt(pc)[2]);


            if (Integer.parseInt(lines.elementAt(pc)[0]) != pc) {
                if (modus == 1) System.out.println(l0 + " " + l1 + " " + l2);
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
                        System.out.println(l0 + " " + l1 + " " + l2 + ": io[1](" + io[1] + ")=accu(" + accu + ")");
                    io[1] = accu;
                    System.out.println(io[1]);
                    break;
                case "IN":
                    if (modus == 1)
                        System.out.println(l0 + " " + l1 + " " + l2 + ": accu(" + accu + ")=io[0](" + io[0] + ")");
                    io[0] = scn.nextFloat();
                    accu = io[0];
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

        if(Integer.parseInt(lines.lastElement()[0])!=pc){
            throw new Exception("syntax-error at line " + pc + ": wrong line number.");
        }

        if(lines.lastElement()[1].equals("STOP")){
            if (modus == 1) System.out.println(lines.lastElement()[0] + " " + lines.lastElement()[1] + ": stop");
        }
        else{
            throw new Exception("semantic-error at line " + pc + ": program not ending");
        }

        long end = System.nanoTime();
        long duration = (end-start)/1000000000;
        System.out.println("Laufzeit: "+duration+"s");
        return;
    }
}
