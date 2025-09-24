public class main {

    public static void main(String[] args) {

        try{
            ProcessBuilder pb =  new ProcessBuilder("notepad.exe");

            Process p = pb.start();

        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }


}
