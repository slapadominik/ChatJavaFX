public class Main {
    public static void main(String[] args) {
        int port;
        ChatServer chatServer = new ChatServer();
        if (args.length!=1){
            System.out.println("Usage: java Main [port]");
            port = 8000;
            System.out.println("Default port:"+port);
        }
        else{
            port = Integer.parseInt(args[0]);
            System.out.println("Port: "+port);
        }
        chatServer.go(port);
    }
}
