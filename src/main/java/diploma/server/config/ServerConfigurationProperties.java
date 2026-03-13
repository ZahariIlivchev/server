package diploma.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("zdiploma-server-config")
public class ServerConfigurationProperties {
    // private String domain;
    private String fileStorageLocation;
    private String clientLocation;
    private String jwtSecret;
    // private String available_storage_per_account_gb;

    public void setFileStorageLocation(String fileStorageLocation) {
        this.fileStorageLocation = fileStorageLocation;
    }

    public String getFileStorageLocation() {
        return fileStorageLocation;
    }

    public void setClientLocation(String clientLocation) {
        this.clientLocation = clientLocation;
    }

    public String getClientLocation() {
        return clientLocation;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }
}
