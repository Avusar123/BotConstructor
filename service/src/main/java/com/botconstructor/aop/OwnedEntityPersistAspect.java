package com.botconstructor.aop;


import com.botconstructor.model.user.OwnedEntity;
import com.botconstructor.model.user.UserModel;
import org.apache.tomcat.websocket.AuthenticationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OwnedEntityPersistAspect {

    @Around("execution(* com.botconstructor.persistence.repos" +
            "..save(..)) && args(entity)")
    public Object assignOwnerBeforeSave(ProceedingJoinPoint joinPoint, Object entity) throws Throwable {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (entity instanceof OwnedEntity) {
            if (authentication != null && authentication.isAuthenticated()) {
                ((OwnedEntity) entity).setOwner((UserModel) authentication.getPrincipal());
            } else {
                throw new AuthenticationException("User must be authenticated to save this entity");
            }
        }

        return joinPoint.proceed(new Object[]{entity});
    }
}
