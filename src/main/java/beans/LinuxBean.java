package beans;

/**
 * @Auther: mq
 * @Date: 2018/9/14 12:00
 */
public class LinuxBean implements Bean{
    @Override
    public void doService() {
        System.out.println("linux");
    }
}
