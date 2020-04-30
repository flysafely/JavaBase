package JavaUtilConcurrent.ReentrantLock;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.concurrent.Callable;

public class DirectBytebuffer implements Callable {

    @Override
    public Object call() throws Exception {
        int time = 10000000;
        ByteBuffer buffer2 = ByteBuffer.allocateDirect(2*time);
        Date begin = new Date();
        for(int i=0;i<time;i++){
            buffer2.putChar('a');
        }
        buffer2.flip();
        for(int i=0;i<time;i++){
            buffer2.getChar();
        }
        Date end = new Date();
        return end.getTime()-begin.getTime();
    }
}
