public class Test {
    public static void main(String[] args) {

       String line = "ziomek left the chat room.";
       boolean reuslt = line.contains("left the chat room.");
       if (reuslt){
           System.out.println("Zawiera");
       }
       else{
           System.out.println("Nie zawiera");
       }
    }
}
