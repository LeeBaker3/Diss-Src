package com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation;

import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseEntity_;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.Project;
import com.mycompany.atomicinformationconfigurationmanager.entities.typeofatomicinformation.Typeofatomicinformation;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-20T23:51:29")
@StaticMetamodel(Atomicinformation.class)
public class Atomicinformation_ extends BaseEntity_ {

    public static volatile SingularAttribute<Atomicinformation, String> content;
    public static volatile SingularAttribute<Atomicinformation, Project> projectID;
    public static volatile SingularAttribute<Atomicinformation, Typeofatomicinformation> typeOfAtomicInformationID;

}