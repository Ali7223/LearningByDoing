package testingmethods;

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

}
