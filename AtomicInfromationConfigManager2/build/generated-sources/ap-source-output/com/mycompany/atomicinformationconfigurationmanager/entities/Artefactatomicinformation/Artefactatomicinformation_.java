package com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.Artefactatomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.Atomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseEntity_;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-14T21:11:07")
=======
@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-07T22:18:59")
>>>>>>> FETCH_HEAD
@StaticMetamodel(Artefactatomicinformation.class)
public class Artefactatomicinformation_ extends BaseEntity_ {

    public static volatile SingularAttribute<Artefactatomicinformation, Artefact> artefactID;
    public static volatile SingularAttribute<Artefactatomicinformation, Atomicinformation> atomicInformationID;
    public static volatile CollectionAttribute<Artefactatomicinformation, Artefactatomicinformation> artefactatomicinformationCollection;

}