package domain.service;

import javax.ws.rs.core.HttpHeaders;

import java.util.Date;

public interface KeycloakService {
	public boolean verifyAuthentification(HttpHeaders headers, Date now);
	public String getToken(HttpHeaders headers);
	public String getIdUser(String token);

}
