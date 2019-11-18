package github.chenjiangchaoAndYujunpo.Core;

import org.junit.Test;

import java.util.Arrays;

public class TransformTest {

    @Test
    public void toPostfixNotation() {
//        String str = "1.2*(1.9+6.1/3.1-1.5)+1.4";
        String str = "1";
        String[] chars = Transform.ToPostfixNotation(str);
        System.out.println(Arrays.toString(chars));
    }
}