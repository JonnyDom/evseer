package com.domingues4j.evseer.external.powerdevice;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Getter
@Component
public class VariableListFactory {

    private final List<String> variableList;

    public VariableListFactory() {
        this.variableList = Arrays.stream(ValueType.values()).map(ValueType::getLabel).toList();
    }
}
