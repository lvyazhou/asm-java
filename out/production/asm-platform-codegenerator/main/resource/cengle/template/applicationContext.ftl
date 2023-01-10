<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context='http://www.springframework.org/schema/context'
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
                            http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    
    <context:component-scan base-package="com.ikongjian.*">
        <context:exclude-filter type="annotation"  expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <context:property-placeholder location="classpath*:*.properties" />

    <bean class="com.ikongjian.hades.service.impl.SequenceServiceImpl"/>
    <bean id="goodsBatchServiceImpl" class="com.ikongjian.hades.service.impl.GoodsBatchServiceImpl"/>
    
    <bean id="goodsBrandServiceImpl" class="com.ikongjian.hades.service.impl.GoodsBrandServiceImpl"/>

    <bean id="procurementSettingServiceImpl" class="com.ikongjian.hades.service.impl.ProcurementSettingServiceImpl"/>

    <bean id="taxServiceImpl" class="com.ikongjian.hades.service.impl.TaxServiceImpl"/>

    <bean id="regionServiceImpl" class="com.ikongjian.hades.service.impl.RegionServiceImpl"/>

    <bean id="goodsCategoryServiceImpl" class="com.ikongjian.hades.service.impl.GoodsCategoryServiceImpl"/>
    <bean id="globalDictionaryServiceImpl" class="com.ikongjian.hades.service.impl.DictionaryServiceImpl"/>
    <bean id="dictionarySelectServiceImpl" class="com.ikongjian.hades.service.cache.DictionarySelectServiceImpl"/>

    <bean id="goodsInfoServiceImpl" class="com.ikongjian.hades.service.impl.GoodsInfoServiceImpl"/>

    <bean id="StorageLocationServiceImpl" class="com.ikongjian.hades.service.impl.StorageLocationServiceImpl"/>
    
    <bean id="sendScheduleServiceImpl" class="com.ikongjian.hades.service.impl.SendScheduleServiceImpl"/>

    <bean id="ISuitServiceImpl" class="com.ikongjian.hades.service.impl.ISuitServiceImpl"/>


    <bean id="supplierServiceImpl" class="com.ikongjian.hades.service.impl.SupplierServiceImpl"/>

    <bean id="settleStoreOwnerTypeServiceImpl" class="com.ikongjian.hades.service.impl.SettleStoreOwnerTypeServiceImpl"/>


    <import resource="dubbo.xml"/>
    <import resource="databasePool.xml"/>
    <import resource="classpath:common-util.xml"/>
   <!--  <import resource="classpath:common-mq.xml"/>
    <import resource="classpath:bizlog-mq.xml"/>
    <import resource="hades-mq.xml"/> -->
</beans>