package com.mycompany.atomicinformationconfigurationmanager.entities.base;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-24T22:51:01")
@StaticMetamodel(BaseEntity.class)
public class BaseEntity_ { 

    public static volatile SingularAttribute<BaseEntity, Integer> id;
    public static volatile SingularAttribute<BaseEntity, Boolean> isCurrentVersion;
    public static volatile SingularAttribute<BaseEntity, Boolean> entityActive;
    public static volatile SingularAttribute<BaseEntity, Integer> previousVersionReference;
    public static volatile SingularAttribute<BaseEntity, Integer> versionNumber;

}