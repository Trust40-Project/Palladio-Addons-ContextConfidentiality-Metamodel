package org.palladiosimulator.pcm.confidentiality.context.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.pcm.confidentiality.context.ContextPackage;
import org.palladiosimulator.pcm.confidentiality.context.model.ModelFactory;

class CheckAccessRightsSingleAttributeTest {

    @BeforeAll
    static void init() {
        ContextPackage.eINSTANCE.eClass();
    }

    @Test
    void testSingleAttributeNull() {
        var requestSingleType = ModelFactory.eINSTANCE.createSingleAttributeContext();
        var poliySingleType = ModelFactory.eINSTANCE.createSingleAttributeContext();
        assertTrue(poliySingleType.checkAccessRight(requestSingleType), "Comparison failed");
    }

    @Test
    void testSingleAttributeName() {
        var requestSingleType = ModelFactory.eINSTANCE.createSingleAttributeContext();
        var poliySingleType = ModelFactory.eINSTANCE.createSingleAttributeContext();
        var poliySingleTypeName = ModelFactory.eINSTANCE.createSingleAttributeContext();
        requestSingleType.setEntityName("Test");
        poliySingleTypeName.setEntityName("Test");
        assertFalse(poliySingleType.checkAccessRight(requestSingleType));
        assertTrue(poliySingleTypeName.checkAccessRight(requestSingleType));
    }

    @Test
    void testSingleAttributeSame() {
        var requestSingleType = ModelFactory.eINSTANCE.createSingleAttributeContext();
        assertTrue(requestSingleType.checkAccessRight(requestSingleType), "Comparison failed");
    }
    
    @Test
    void testSingleAttributeDifferentContext() {
        var requestSingleType = ModelFactory.eINSTANCE.createSingleAttributeContext();
        var poliyHierachicalType = ModelFactory.eINSTANCE.createHierarchicalContext();
        assertFalse(poliyHierachicalType.checkAccessRight(requestSingleType), "Comparison failed");
    }
    
    @Test
    void testSingleAttributeSameNameDifferentTypeNull() {
        var requestSingleType = ModelFactory.eINSTANCE.createSingleAttributeContext();
        var poliyHierachicalType = ModelFactory.eINSTANCE.createSingleAttributeContext();
        var type = ModelFactory.eINSTANCE.createContextType();
        requestSingleType.setContexttype(type);
        requestSingleType.setEntityName("Test");
        poliyHierachicalType.setEntityName("Test");
        assertFalse(poliyHierachicalType.checkAccessRight(requestSingleType), "Comparison failed");
    }
    @Test
    void testSingleAttributeSameNameDifferentType() {
        var requestSingleType = ModelFactory.eINSTANCE.createSingleAttributeContext();
        var poliyHierachicalType = ModelFactory.eINSTANCE.createSingleAttributeContext();
        var type = ModelFactory.eINSTANCE.createContextType();
        var type2 = ModelFactory.eINSTANCE.createContextType();
        requestSingleType.setContexttype(type);
        poliyHierachicalType.setContexttype(type2);
        requestSingleType.setEntityName("Test");
        poliyHierachicalType.setEntityName("Test");
        assertFalse(poliyHierachicalType.checkAccessRight(requestSingleType), "Comparison failed");
    }
    @Test
    void testSingleAttributeSameType() {
        var requestSingleType = ModelFactory.eINSTANCE.createSingleAttributeContext();
        var poliyHierachicalType = ModelFactory.eINSTANCE.createSingleAttributeContext();
        var type = ModelFactory.eINSTANCE.createContextType();
        requestSingleType.setContexttype(type);
        poliyHierachicalType.setContexttype(type);
        requestSingleType.setEntityName("Test");
        poliyHierachicalType.setEntityName("Test");
        assertTrue(poliyHierachicalType.checkAccessRight(requestSingleType), "Comparison failed");
    }

}
