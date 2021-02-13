package com.bdg.service.user;


public interface CreateSupported<REQUEST, RESPONSE> {

    RESPONSE add(REQUEST request);
}
