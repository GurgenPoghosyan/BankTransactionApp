package com.bdg.service;

public interface UpdateSupported<ID,REQUEST,RESPONSE> {

    RESPONSE update(ID id,REQUEST request);
}
