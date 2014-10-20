package com.mycompany.atomicinformationconfigurationmanager.entities.project;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.Atomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseEntity_;
import com.mycompany.atomicinformationconfigurationmanager.entities.distributionrecipient.Distributionrecipient;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-20T23:51:29")
@StaticMetamodel(Project.class)
public class Project_ extends BaseEntity_ {

    public static volatile CollectionAttribute<Project, Atomicinformation> atomicinformationCollection;
    public static volatile CollectionAttribute<Project, Artefact> artefactCollection;
    public static volatile SingularAttribute<Project, String> projectName;
    public static volatile SingularAttribute<Project, String> projectReference;
    public static volatile CollectionAttribute<Project, Distributionrecipient> distributionrecipientCollection;

}