package testingmethods;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO alsaad This type ...
 *
 */
public class TestClass {
  static Logger logger = LoggerFactory.getLogger(TestClass.class);

  public void checkloggers() {

    logger.info("in check loggers");

  }

  // To check if the class name has a table name
  public String getEntityTableName(String className) {

    if (!className.endsWith("Entity")) {
      logger.error("Could not return table name because {} is not an Entity class", className);
      return null;
    }
    try {
      Class<?> entityClass = Class.forName(className);
      Table table = entityClass.getAnnotation(Table.class);
      return table == null
          ? StringUtils.left(entityClass.getSimpleName(), entityClass.getSimpleName().length() - "Entity".length())
          : table.name();
    } catch (ClassNotFoundException e) {
      logger.error("{}: Could not find {}", e.getMessage(), className);
      return null;
    }
  }

  List<Field> getAllFields(List<Field> fields, Class<?> cl) {

    fields.addAll(Arrays.asList(cl.getDeclaredFields()));

    if (cl.getSuperclass() != null) {
      getAllFields(fields, cl.getSuperclass());
    }

    return fields;
  }

  /**
   * Helper method to get type of a field inclusive field from super classes
   *
   * @param pojoClass {@link Class} the class object of the pojo
   * @param fieldName {@link String} the name of the field
   * @return type of the field
   */
  Class<?> getTypeOfField(Class<?> pojoClass, String fieldName) {

    if (pojoClass != null) {
      List<Field> fields = new ArrayList();
      getAllFields(fields, pojoClass);

      Optional<Field> field = fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst();

      if (field.isPresent()) {
        return field.get().getType();
      }
    }
    logger.error("Could not find type of field {}", fieldName);
    return null;
  }

  /*
   * **
   *
   * @param pojoClass {@link Class} the class object of the pojo
   *
   * @param fieldName {@link String} the name of the field
   *
   * @return true if the field is an instance of java.utils.Collections
   */
  boolean isCollection2(Class<?> pojoClass, String fieldName) {

    Class<?> type = getTypeOfField(pojoClass, fieldName);
    return type == null ? false : Collection.class.isAssignableFrom(type);
  }

  String getCanonicalNameOfField(String className, String fieldName) {

    try {
      Class<?> entityClass = Class.forName(className);
      Class<?> type = getTypeOfField(entityClass, fieldName);
      if (type != null) {
        return type.getCanonicalName();
      }
    } catch (ClassNotFoundException e) {
      logger.error("{}: Could not find {}", e.getMessage(), className);
    }
    return null;
  }

}
