package testingmethods;

import org.apache.commons.lang3.StringUtils;

/**
 * TODO alsaad This type ...
 *
 */

class a {

}

public class TestingMethodsOfJavaUtil {

  @SuppressWarnings("javadoc")
  public static void main(String[] args) throws Exception {

    TestClass tc = new TestClass();
    tc.checkloggers();

    Class<?> c = Class.forName("testingmethods.TestClass");
    String str = StringUtils.left(c.getSimpleName(), c.getSimpleName().length() - "Class".length());

    System.out.println(str);

  }

}