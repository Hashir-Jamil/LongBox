package org.longbox.domainobjects.dto;

import java.util.List;

public interface JsonConvertor {

    void serializeStubData();

    <T> List<T> deserializeStubData(String filepath);
}
