package com.bdg.service.user;

public interface CRUDService<C,U,R,I> extends
        CreateSupported<C, R>,
        GetSupported<I,R>,
        DeleteSupported<I>,
        UpdateSupported<I, U,R> {
}
