import net.bytebuddy.asm.Advice;

import java.util.PriorityQueue;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PriorityQueueTest {
    static Stream<Arguments>streamProvider(){
        return Stream.of(
                Arguments.of(new int[]{1,2,3,4,5},new int[]{1,2,3,4,5}),
                Arguments.of(new int[]{5,0,6,9},new int[]{0,5,6,9}),
                Arguments.of(new int[]{3,5,7,1,2},new int[]{1,2,3,5,7}),
                Arguments.of(new int[]{200,100,200},new int[]{100,200,200}),
                Arguments.of(new int[]{5,4,3,2,1},new int[]{1,2,3,4,5})
                );
    }
    @ParameterizedTest(name="#{index} - Test with Argument={0}.{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_test(int[] random_array, int[] correct_array){
        int[] result = new int[random_array.length];
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        for(int i = 0; i < random_array.length;i++)
        {
            test.add(random_array[i]);
        }
        for(int i = 0; i < random_array.length;i++)
        {
            result[i] = test.poll();
        }
        assertArrayEquals(result,correct_array);

    }

    @Test
    public void classcast(){
        Exception exception = assertThrows(ClassCastException.class,()->{
            PriorityQueue test = new PriorityQueue();
            test.add(1);
            test.add(2);
        });
    }

    @Test
    public void nullpointer(){
        Exception exception = assertThrows(NullPointerException.class,()->{
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            test.add(null);
        });
    }

    @Test
    public void illegal(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
           PriorityQueue test = new PriorityQueue<>(0);
        });
    }


}
