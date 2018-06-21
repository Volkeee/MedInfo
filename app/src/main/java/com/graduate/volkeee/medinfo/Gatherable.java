package com.graduate.volkeee.medinfo;

import com.graduate.volkeee.medinfo.model.Account;

public interface Gatherable {
    void gatherData(Account account);
    boolean validate();
}
