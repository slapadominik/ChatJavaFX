public class Test {
    public static void main(String[] args) {

        String line = "***SERVER: ziom entered the chat room!***\n";
        System.out.println(line);
        String[] split = line.split(" ");
        String name = split[1];
        System.out.println(name);
    }
}
