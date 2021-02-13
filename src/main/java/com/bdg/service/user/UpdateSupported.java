package com.bdg.service.user;

public interface UpdateSupported<ID,REQUEST,RESPONSE> {

    RESPONSE update(ID id,REQUEST request);
}
