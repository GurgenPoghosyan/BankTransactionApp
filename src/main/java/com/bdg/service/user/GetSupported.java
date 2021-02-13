package com.bdg.service.user;

public interface GetSupported<ID,RESPONSE> {

    RESPONSE get(ID id);
}
