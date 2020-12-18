package ehb.alvi.afbackendapi.config;

import ehb.alvi.afbackendapi.entity.Product;
import ehb.alvi.afbackendapi.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


//Zorgt ervoor dat Spring dit ziet als een configuratie
@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public DataRestConfig(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    //Dit stukje code zorgt ervoor dat de PUT, DELETE & POST http methods niet bereikbaar zijn
    //Hiermee overriden we de default REST API configuratie
    //Alleen de GET method zal werken, dus de API is read-only
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        HttpMethod[] unSupportedActions = {HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.POST};
        // HTTP methods uitzetten voor Product: PUT, POST, DELETE
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metdata,httpMethods) -> httpMethods.disable(unSupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unSupportedActions));

        // HTTP methods uitzetten voor ProductCategory: PUT, POST, DELETE
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metdata,httpMethods) -> httpMethods.disable(unSupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unSupportedActions));
        // internal helper method call to expose the ids
        exposeIds(config);
    }

    public void exposeIds(RepositoryRestConfiguration config) {

        // entity ids exposen


        // get list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // get the entity types for the entities
        for(EntityType tempEntityType: entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        // entity ids exposen voor de array van entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);


    }
}
