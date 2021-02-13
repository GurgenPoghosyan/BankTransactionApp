package com.bdg.service;

import com.bdg.transform.request.user.UserCreateRequest;
import com.bdg.transform.request.user.UserUpdateRequest;
import com.bdg.transform.response.user.UserResponse;

public interface CRUDService<C,U,R,I> extends
        CreateSupported<C, R>,
        GetSupported<I,R>,
        DeleteSupported<I>,
        UpdateSupported<I, U,R> {
}
