package ehb.alvi.afbackendapi.config;

import ehb.alvi.afbackendapi.entity.Product;
import ehb.alvi.afbackendapi.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;


//Zorgt ervoor dat Spring dit ziet als een configuratie
@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

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

    }
}
