package testingmethods;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;

public class TestingMethodsOfJavaUtil {

  @SuppressWarnings("javadoc")
  public static void main(String[] args) throws Exception {

    TestClass tc = new TestClass();
    tc.checkloggers();// 1

    Class<?> c = Class.forName("testingmethods.TestClass");
    String str = StringUtils.left(c.getSimpleName(), c.getSimpleName().length() - "Class".length());

    System.out.println(str);// 2

    Class<?> classname = Class.forName("resources.SampleEntity");

    String cite = tc.getEntityTableName(classname.getCanonicalName());// to check if table annotation exist
    System.out.println(cite);// 3

    List<Field> fields1 = new ArrayList<>();

    tc.getAllFields(fields1, classname);
    System.out.println("4 : " + fields1.get(1).isAnnotationPresent(Id.class));

    Class<?> check = tc.getTypeOfField(classname, "aname");
    System.out.println(check);// 4

    System.out.println(fields1);// 5
    System.out.println(check.getTypeName());// 6
    System.out.println(tc.isCollection2(classname, "aname"));//
    System.out.println("9 : " + tc.getCanonicalNameOfField("resources.SampleEntity", "aname"));// 9

    Optional<Method> getterOptional = Arrays.stream(classname.getMethods())
        .filter(m -> m.getName().equals(
            "get" + fields1.get(0).getName().substring(0, 1).toUpperCase() + fields1.get(0).getName().substring(1))
            && !m.isAnnotationPresent(Id.class))
        .findFirst();

    System.out.println("10 : " + getterOptional);

  }

}