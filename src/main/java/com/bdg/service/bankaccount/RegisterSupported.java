package com.bdg.service.bankaccount;

public interface RegisterSupported<REQUEST,RESPONSE> {

    RESPONSE register(REQUEST request);
}
