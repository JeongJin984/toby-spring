package springbook.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Provider;

@Service
public class ServiceRequestProvider {
    @Autowired
    Provider<ServiceRequest> provider;

    public ServiceRequest getProvider() {
        return provider.get();
    }
}
