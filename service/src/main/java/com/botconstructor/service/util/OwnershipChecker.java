package com.botconstructor.service.util;

import com.botconstructor.model.user.OwnedEntity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("ownershipChecker")
public class OwnershipChecker {

    @Autowired
    private EntityManager entityManager;

    public boolean isOwner(UUID entityId,
                           Class<?> entityType) {
        var entity = entityManager.find(entityType, entityId);

        if (entity instanceof OwnedEntity ownedEntity) {
            var owner = ownedEntity.getOwner();

            var username = SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getName();

            return username.equals(owner.getUsername());
        }

        return true;
    }
}
