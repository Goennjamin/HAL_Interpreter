import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

public class hal_os {



    public static void main(String[] args) throws Exception {


        File file = new File("/home/leonw/Dokumente/HAL_Konfig.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String str;

        String[] Prozessoren[] = new String[20][];

        String[] Verbindungen[] = new String[20][];

        Vector<String[]> lines = new Vector<>();
        while ((str = br.readLine()) != null) {
            lines.add(str.split(" "));
        }

        boolean Verbindungscheck = false;

        for(int i = 1; i < lines.size(); i++){

            if(lines.elementAt(i)[0].equals("HAL-Verbindungen:")){
                Verbindungscheck = true;
            }


            if(Verbindungscheck){
                Verbindungen[i-1] = lines.elementAt(i);
            }else{
                Prozessoren[i-1] = lines.elementAt(i);
            }

        }

        System.out.println(Verbindungen);

    }
}
