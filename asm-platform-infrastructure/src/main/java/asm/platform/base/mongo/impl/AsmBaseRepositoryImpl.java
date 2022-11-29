package asm.platform.base.mongo.impl;

import asm.platform.base.mongo.AsmBaseOperations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.repository.NoRepositoryBean;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@NoRepositoryBean
public class AsmBaseRepositoryImpl<T, ID extends Serializable> implements AsmBaseOperations<T, Long> {

    @Resource
    private MongoOperations mongoOperations;

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private MongoEntityInformation<T, ID> entityInformation;

    protected Class<T> getEntityClass() {
        return entityInformation.getJavaType();
    }

    public Class<T> getGenericClass() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

//    @Override
//    public Page<T> findPageByQuery(Query query, Pageable pageable) {
//        long total = mongoTemplate.count(query,getEntityClass());
//        List<T> list = mongoTemplate.find(query.with(pageable),getEntityClass());
//        return new PageImpl<T>(list, pageable, total);
//    }
//
//    @Override
//    public Page<T> findPageByCriteria(Criteria criteria, Pageable pageable) {
//        return findPageByQuery(new Query(criteria), pageable);
//    }

    @Override
    public void helloWord() {
        System.out.println("helloword " + getGenericClass());
    }
}
