package github.chenjiangchao.Core;

import org.junit.Test;

import java.util.Arrays;

public class TransformTest {

    @Test
    public void toPostfixNotation() {
        String str = "12*(19+61/31-15)+14";
        String[] chars = Transform.ToPostfixNotation(str);
        System.out.println(Arrays.toString(chars));
    }
}