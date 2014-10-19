package com.mycompany.atomicinformationconfigurationmanager.entities.Artefact;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.Artefactatomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution.Artefactdistribution;
import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseEntity_;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.Project;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-14T21:11:07")
=======
@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-07T22:18:59")
>>>>>>> FETCH_HEAD
@StaticMetamodel(Artefact.class)
public class Artefact_ extends BaseEntity_ {

    public static volatile CollectionAttribute<Artefact, Artefactdistribution> artefactdistributionCollection;
    public static volatile SingularAttribute<Artefact, Project> projectID;
    public static volatile SingularAttribute<Artefact, String> artefactName;
    public static volatile SingularAttribute<Artefact, String> artefactMajorVersionNumber;
    public static volatile SingularAttribute<Artefact, String> artefactMinorVersionNumber;
    public static volatile SingularAttribute<Artefact, byte[]> artefactFile;
    public static volatile SingularAttribute<Artefact, String> artefactFilename;
    public static volatile CollectionAttribute<Artefact, Artefactatomicinformation> artefactatomicinformationCollection;

}