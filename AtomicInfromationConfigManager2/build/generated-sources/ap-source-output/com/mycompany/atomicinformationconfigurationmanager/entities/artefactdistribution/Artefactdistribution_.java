package com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseEntity_;
import com.mycompany.atomicinformationconfigurationmanager.entities.distributionrecipient.Distributionrecipient;
import com.mycompany.atomicinformationconfigurationmanager.entities.methodofdistribution.Methodofdistribution;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-09-22T23:05:12")
@StaticMetamodel(Artefactdistribution.class)
public class Artefactdistribution_ extends BaseEntity_ {

    public static volatile SingularAttribute<Artefactdistribution, Artefact> artefactID;
    public static volatile SingularAttribute<Artefactdistribution, Date> dateOfArtefactDistribution;
    public static volatile SingularAttribute<Artefactdistribution, Methodofdistribution> methodOfDistributionID;
    public static volatile SingularAttribute<Artefactdistribution, Distributionrecipient> distributionRecipientID;

}