package sq.test_instrumentation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sp01 on 2017/5/11.
 */
public class CtestTest {

    private Ctest ctest;

    @Before
    public void setUp() throws Exception {
        ctest = new Ctest();
    }

    @Test
    public void xx() throws Exception {
        int expected = 1;

        assertEquals(expected,ctest.xx());
    }

}