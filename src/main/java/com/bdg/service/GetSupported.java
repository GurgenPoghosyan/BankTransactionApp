package com.bdg.service;

public interface GetSupported<ID,RESPONSE> {

    RESPONSE get(ID id);
}
