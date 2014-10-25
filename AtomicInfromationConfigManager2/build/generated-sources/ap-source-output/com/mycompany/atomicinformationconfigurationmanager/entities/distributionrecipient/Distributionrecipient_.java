package com.mycompany.atomicinformationconfigurationmanager.entities.distributionrecipient;

import com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution.Artefactdistribution;
import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseEntity_;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.Project;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-24T22:51:01")
@StaticMetamodel(Distributionrecipient.class)
public class Distributionrecipient_ extends BaseEntity_ {

    public static volatile SingularAttribute<Distributionrecipient, String> eMailAddress;
    public static volatile CollectionAttribute<Distributionrecipient, Artefactdistribution> artefactdistributionCollection;
    public static volatile SingularAttribute<Distributionrecipient, Project> projectID;
    public static volatile SingularAttribute<Distributionrecipient, String> officeNumber;
    public static volatile SingularAttribute<Distributionrecipient, String> surname;
    public static volatile SingularAttribute<Distributionrecipient, String> firstName;
    public static volatile SingularAttribute<Distributionrecipient, String> mobileNumber;
    public static volatile SingularAttribute<Distributionrecipient, String> companyName;

}