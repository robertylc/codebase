package DataBase;

import DataBase.mongodao.MongoPrimaryKey;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import org.apache.commons.collections.map.HashedMap;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by robertpicyu on 2017/10/20.
 */
public class MongoDBUtility<T>{
    protected MongoTemplate mongoTemplate;
    //public static final Logger logger = LoggerFactory.getLogger(MongoDBUtility.class);

    private static Cache<Class,MongoCollectionInfo> collectionInfoCache =
            CacheBuilder.newBuilder().maximumSize(100).build();

    public void createCollection(Class cls) {

        if (!this.mongoTemplate.collectionExists(cls)) {
            this.mongoTemplate.createCollection(cls);
        }
    }

    public void upsertByPrimativeKey(T record) throws IllegalAccessException {
        Update update = new Update();
        Query query = new Query();

        createCollection(record.getClass());

        Map<String, Object> eqMap = new HashMap<String, Object>();
        Map<String, Object> keyValues = getMongoPrimativeKeyAndValue(record);
        if (keyValues == null ||keyValues.size() == 0) {  //record没有复合主键，可以直接插入
            this.mongoTemplate.insert(record);
            return;
        }

        keyValues.forEach((key,val)->eqMap.put(key,val));
        Criteria criteria = new Criteria();
        eqMap.forEach((key,val)->criteria.andOperator(Criteria.where(key).is(val)));
        query.addCriteria(criteria);

        keyValues = getMongoAllKeyAndValues(record);
        keyValues.forEach((key,val)->{if(val != null) update.set(key,val);});  //只更新or 插入非空字段

        this.mongoTemplate.upsert(query,update,record.getClass());
    }

    public void deleteByPrimativeKey(T data){
        //TODO: add your code here
    }

    public List<T> getListByPrimativeKey(T record){
        Criteria criteria = new Criteria();
        List<Criteria> criterias = new ArrayList<Criteria>();
        Map<String, Object> eqMap = new HashMap<String, Object>();
        Map<String, Object> keyValues = null;
        try {
            keyValues = getMongoPrimativeKeyAndValue(record);
            keyValues.forEach((key,val)->criterias.add(Criteria.where(key).is(val)));
            criteria.andOperator(criterias.toArray(new Criteria[criterias.size()]));
            List result = this.mongoTemplate.find(new Query(criteria),record.getClass());
            return result;
        } catch (IllegalAccessException e) {
            //logger.error("fail get primary key value");
        }
        return null;
    }

    private List<T> getElements(Criteria criteria,Class<T> cls){
        return this.mongoTemplate.find(new Query(criteria),cls);
    }

    public List<T> getListByPrimativeKeyWithQuerry(T data,PPMongoQuerry querry){
        //TODO: add your code here
        return new LinkedList<T>();
    }


    /**
     * 提取record中的主键值（参照MySQL的概念，主键对应唯一一条记录）
     * @param record : MongoDB中 collection 对应的 Class
     * @return Map<primaryKeyName, value>
     */
    private Map<String,Object> getMongoPrimativeKeyAndValue(T record) throws IllegalAccessException {
        MongoCollectionInfo collectionInfo = getCollectionInfo(record.getClass());
        List<Field> fields = collectionInfo.getPrimaryKeyFields();
        Map<String,Object> result = new HashedMap();
        for (Field field:fields){
            field.setAccessible(true);
            result.put(field.getName(),field.get(record));
        }
        return result;
    }

    private Map<String,Object> getMongoAllKeyAndValues(T record) throws IllegalAccessException {
        MongoCollectionInfo collectionInfo = getCollectionInfo(record.getClass());
        List<Field> fields = collectionInfo.getCollectionFields();
        Map<String,Object> result = new HashedMap();
        for (Field field:fields){
            field.setAccessible(true);
            result.put(field.getName(),field.get(record));
        }
        return result;
    }


    /**
     *  根据reocrd 的类型 得到对应MongoDB collection的信息：包括主键
     *  */
    private MongoCollectionInfo getCollectionInfo(Class cls){
        MongoCollectionInfo collectionInfo = collectionInfoCache.getIfPresent(cls);
        if (collectionInfo != null) {
            return collectionInfo;
        }

        collectionInfo = new MongoCollectionInfo();

        Field[] fields = cls.getDeclaredFields();
        List<Field> primaryKeys = new LinkedList<Field>();
        for (Field field:fields) {
            if (field.isAnnotationPresent(MongoPrimaryKey.class)){
                primaryKeys.add(field);
            }
        }
        collectionInfo.setCollectionName(cls.getSimpleName());
        collectionInfo.setCollectionClass(cls);
        collectionInfo.setCollectionFields(Lists.newArrayList(fields));
        collectionInfo.setPrimaryKeyFields(primaryKeys);

        collectionInfoCache.put(cls,collectionInfo);
        return collectionInfo;
    }

    public PPMongoQuerry buildQuerry(){
        return new PPMongoQuerry();
    }

    /**
     * Created by robertpicyu on 2017/9/27.
     */
    public class PPMongoQuerry {
        Criteria criteria = new Criteria();

        public PPMongoQuerry() {
        }

        /**
         * 构建筛选条件（支持流式调用）
         * @param record : 精确匹配record中 @MongoPrimaryKey 注解的值
         * @return
         */

        public PPMongoQuerry querryByPrimaryKey(T record){
            List<Criteria> criterias = new ArrayList<Criteria>();
            Map<String, Object> eqMap = new HashMap<String, Object>();
            Map<String, Object> keyValues = null;
            try {
                keyValues = getMongoPrimativeKeyAndValue(record);
                keyValues.forEach((key,val)->criterias.add(Criteria.where(key).is(val)));
                criteria.andOperator(criterias.toArray(new Criteria[criterias.size()]));
            } catch (IllegalAccessException e) {
               // logger.error("fail get primary key value");
            }
            return this;
        }

        public PPMongoQuerry querryByRange(String key,Object upper,Object lower){
            Criteria tempCriteria = Criteria.where(key).lte(upper).gte(lower);
            criteria.andOperator(tempCriteria);
            return this;
        }

        public List<T> getList(Class<T> cls){
            return getElements(criteria,cls);
        }
    }
}
class MongoCollectionInfo {
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
