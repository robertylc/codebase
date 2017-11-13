package DataBase.mongodao;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by robertpicyu on 2017/9/27.
 */
public class MongoCollectionInfo {
    String collectionName;
    Class collectionClass;
    List<Field> primaryKeyFields;
    List<Field> collectionFields;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Class getCollectionClass() {
        return collectionClass;
    }

    public void setCollectionClass(Class collectionClass) {
        this.collectionClass = collectionClass;
    }

    public List<Field> getPrimaryKeyFields() {
        return primaryKeyFields;
    }

    public void setPrimaryKeyFields(List<Field> primaryKeyFields) {
        this.primaryKeyFields = primaryKeyFields;
    }

    public List<Field> getCollectionFields() {
        return collectionFields;
    }

    public void setCollectionFields(List<Field> collectionFields) {
        this.collectionFields = collectionFields;
    }
}
