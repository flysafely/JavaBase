package JavaUtilConcurrent.ReentrantLock;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.concurrent.Callable;

public class NonDirectBytebuffer implements Callable {

    @Override
    public Object call() throws Exception {
        int time = 10000000;
        ByteBuffer buffer = ByteBuffer.allocate(2*time);
        Date begin = new Date();
        for(int i=0;i<time;i++){
            buffer.putChar('a');
        }
        buffer.flip();
        for(int i=0;i<time;i++){
            buffer.getChar();
        }
        Date end = new Date();
        return end.getTime()-begin.getTime();
    }
}
