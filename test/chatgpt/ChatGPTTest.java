import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ChatGPTTest {

    private Stack stack;

    @Before
    public void setUp() {
        stack = new Stack(5);
    }

    // ---------------------- TESTS FOR push() ----------------------
    @Test
    public void testPushSingleElement() {
        stack.push(10);
        assertEquals(1, stack.size());
        assertEquals(10, stack.peek());
    }

    @Test
    public void testPushMultipleElements() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size());
        assertEquals(3, stack.peek());
    }

    @Test
    public void testPushToFullCapacity() {
        for (int i = 0; i < 5; i++)
            stack.push(i);
        assertTrue(stack.isFull());
    }

    @Test
    public void testPushBeyondCapacityThrows() {
        for (int i = 0; i < 5; i++)
            stack.push(i);
        assertThrows(IllegalStateException.class, () -> stack.push(6));
    }

    @Test
    public void testPushDoesNotAlterPreviousValues() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.peek());
        stack.pop();
        assertEquals(1, stack.peek());
    }

    @Test
    public void testPushNegativeValues() {
        stack.push(-5);
        assertEquals(-5, stack.peek());
    }

    @Test
    public void testPushZero() {
        stack.push(0);
        assertEquals(0, stack.peek());
    }

    @Test
    public void testPushOrderPreserved() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void testPushUpdatesIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(99);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testPushUpdatesIsFull() {
        for (int i = 0; i < 4; i++)
            stack.push(i);
        assertFalse(stack.isFull());
        stack.push(5);
        assertTrue(stack.isFull());
    }

    // ---------------------- TESTS FOR pop() ----------------------
    @Test
    public void testPopSingleElement() {
        stack.push(10);
        assertEquals(10, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPopMultipleElements() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void testPopEmptyThrows() {
        assertThrows(IllegalStateException.class, () -> stack.pop());
    }

    @Test
    public void testPopAfterFullPush() {
        for (int i = 0; i < 5; i++)
            stack.push(i);
        assertEquals(4, stack.pop());
        assertEquals(4, stack.size());
    }

    @Test
    public void testPopThenPush() {
        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.push(3);
        assertEquals(3, stack.peek());
    }

    @Test
    public void testPopReducesSize() {
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertEquals(1, stack.size());
    }

    @Test
    public void testPopDoesNotReturnWrongValue() {
        stack.push(99);
        assertNotEquals(50, stack.pop());
    }

    @Test
    public void testPopSequenceMaintainsLIFO() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    public void testPopAfterMultipleOperations() {
        stack.push(5);
        stack.push(10);
        stack.pop();
        stack.push(15);
        assertEquals(15, stack.pop());
        assertEquals(5, stack.pop());
    }

    @Test
    public void testPopEmptyAfterClearing() {
        stack.push(1);
        stack.pop();
        assertThrows(IllegalStateException.class, () -> stack.pop());
    }

    // ---------------------- TESTS FOR peek() ----------------------
    @Test
    public void testPeekSingleElement() {
        stack.push(42);
        assertEquals(42, stack.peek());
    }

    @Test
    public void testPeekDoesNotRemoveElement() {
        stack.push(10);
        stack.peek();
        assertEquals(1, stack.size());
    }

    @Test
    public void testPeekAfterMultiplePushes() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.peek());
    }

    @Test
    public void testPeekAfterPop() {
        stack.push(10);
        stack.push(20);
        stack.pop();
        assertEquals(10, stack.peek());
    }

    @Test
    public void testPeekOnEmptyThrows() {
        assertThrows(IllegalStateException.class, () -> stack.peek());
    }

    @Test
    public void testPeekDoesNotChangeSize() {
        stack.push(5);
        stack.peek();
        assertEquals(1, stack.size());
    }

    @Test
    public void testPeekRepeatedlyReturnsSameValue() {
        stack.push(7);
        assertEquals(7, stack.peek());
        assertEquals(7, stack.peek());
    }

    @Test
    public void testPeekAfterFullPushes() {
        for (int i = 0; i < 5; i++)
            stack.push(i);
        assertEquals(4, stack.peek());
    }

    @Test
    public void testPeekAfterIntermixedPushPop() {
        stack.push(10);
        stack.push(20);
        stack.pop();
        stack.push(30);
        assertEquals(30, stack.peek());
    }

    @Test
    public void testPeekDoesNotAffectIsEmpty() {
        stack.push(1);
        stack.peek();
        assertFalse(stack.isEmpty());
    }

    // ---------------------- TESTS FOR isEmpty() ----------------------
    @Test
    public void testIsEmptyInitiallyTrue() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsEmptyAfterPush() {
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testIsEmptyAfterPushAndPop() {
        stack.push(1);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsEmptyAfterMultiplePushesAndPops() {
        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsEmptyAfterPartiallyFilled() {
        stack.push(10);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testIsEmptyAfterFullThenPopAll() {
        for (int i = 0; i < 5; i++)
            stack.push(i);
        for (int i = 0; i < 5; i++)
            stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsEmptyAfterOnePopRemaining() {
        stack.push(10);
        stack.push(20);
        stack.pop();
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testIsEmptyDoesNotAffectState() {
        stack.push(1);
        stack.isEmpty();
        assertEquals(1, stack.size());
    }

    @Test
    public void testIsEmptyAfterPushAndPeek() {
        stack.push(99);
        stack.peek();
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testIsEmptyOnClearedStack() {
        for (int i = 0; i < 3; i++)
            stack.push(i);
        while (!stack.isEmpty())
            stack.pop();
        assertTrue(stack.isEmpty());
    }

    // ---------------------- TESTS FOR isFull() ----------------------
    @Test
    public void testIsFullInitiallyFalse() {
        assertFalse(stack.isFull());
    }

    @Test
    public void testIsFullAfterPartialFill() {
        stack.push(1);
        assertFalse(stack.isFull());
    }

    @Test
    public void testIsFullAtCapacity() {
        for (int i = 0; i < 5; i++)
            stack.push(i);
        assertTrue(stack.isFull());
    }

    @Test
    public void testIsFullAfterPop() {
        for (int i = 0; i < 5; i++)
            stack.push(i);
        stack.pop();
        assertFalse(stack.isFull());
    }

    @Test
    public void testIsFullAfterPushPopPush() {
        for (int i = 0; i < 5; i++)
            stack.push(i);
        stack.pop();
        stack.push(99);
        assertTrue(stack.isFull());
    }

    @Test
    public void testIsFullDoesNotAffectSize() {
        for (int i = 0; i < 4; i++)
            stack.push(i);
        stack.isFull();
        assertEquals(4, stack.size());
    }

    @Test
    public void testIsFullAfterEmpty() {
        assertFalse(stack.isFull());
    }

    @Test
    public void testIsFullAfterRepeatedPushesAndPops() {
        for (int i = 0; i < 5; i++)
            stack.push(i);
        for (int i = 0; i < 3; i++)
            stack.pop();
        assertFalse(stack.isFull());
    }

    @Test
    public void testIsFullAfterReachingCapacityMultipleTimes() {
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < 5; i++)
                stack.push(i);
            assertTrue(stack.isFull());
            while (!stack.isEmpty())
                stack.pop();
        }
    }

    @Test
    public void testIsFullAfterPushBeyondThrows() {
        for (int i = 0; i < 5; i++)
            stack.push(i);
        assertThrows(IllegalStateException.class, () -> stack.push(99));
        assertTrue(stack.isFull());
    }

    // ---------------------- TESTS FOR size() ----------------------
    @Test
    public void testSizeInitiallyZero() {
        assertEquals(0, stack.size());
    }

    @Test
    public void testSizeAfterPush() {
        stack.push(10);
        assertEquals(1, stack.size());
    }

    @Test
    public void testSizeAfterMultiplePushes() {
        for (int i = 0; i < 3; i++)
            stack.push(i);
        assertEquals(3, stack.size());
    }

    @Test
    public void testSizeAfterPushAndPop() {
        stack.push(10);
        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    public void testSizeAfterFullPush() {
        for (int i = 0; i < 5; i++)
            stack.push(i);
        assertEquals(5, stack.size());
    }

    @Test
    public void testSizeAfterPopFromFull() {
        for (int i = 0; i < 5; i++)
            stack.push(i);
        stack.pop();
        assertEquals(4, stack.size());
    }

    @Test
    public void testSizeNotNegative() {
        assertEquals(0, stack.size());
    }

    @Test
    public void testSizeAfterMixedOperations() {
        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.push(3);
        assertEquals(2, stack.size());
    }

    @Test
    public void testSizeAfterAllCleared() {
        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    public void testSizeDoesNotChangeAfterPeek() {
        stack.push(10);
        stack.peek();
        assertEquals(1, stack.size());
    }
}