package com.domingues4j.evseer.external.powerdevice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VariableListFactoryTest {

    @Test
    public void testGetAllVariables(){
        assertEquals(ValueType.values().length, new VariableListFactory().getVariableList().size());
    }
}