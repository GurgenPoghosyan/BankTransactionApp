package com.bdg.service;


public interface CreateSupported<REQUEST, RESPONSE> {

    RESPONSE add(REQUEST request);
}
