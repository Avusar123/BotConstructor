package com.botconstructor.model.user;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class OwnedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    protected UserModel owner;

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }
}
