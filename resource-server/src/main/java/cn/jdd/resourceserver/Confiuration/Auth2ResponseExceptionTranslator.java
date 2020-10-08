package cn.jdd.resourceserver.Confiuration;

import org.aspectj.bridge.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

public class Auth2ResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        Throwable throwable = e.getCause();
        if ( throwable instanceof InvalidTokenException){

        }
        return null;
    }
}
