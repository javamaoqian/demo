import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

/**
 * @Author: mq
 * @Date: 2021/1/19 19:40
 */
public class BIOServer {

    public static void main(String[] args) throws Exception {
     test();
    }

    public static void test() throws Exception {
        Socket socket = new Socket("192.168.88.132", 8877);
        OutputStream os = socket.getOutputStream();
        os.write(7);
        os.flush();
    }
}
