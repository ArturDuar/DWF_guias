package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculadoraTest {

    @Test
    void testSuma(){
        Calculadora calculadora = new Calculadora();
        Double resultado = calculadora.Suma(1.79,1.21);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(3, resultado);
    }

    @Test
    void testResta(){
        Calculadora calculadora = new Calculadora();
        Double resultado = calculadora.Resta(1.79,0.79);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado);
    }

    @Test
    void TestMultiplicacion(){
        Calculadora calculadora = new Calculadora();
        Double resultado = calculadora.Multiplicacion(1.8, 2.5);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(4.5, resultado);
    }

    @Test
    void testDivision(){
        Calculadora calculadora = new Calculadora();
        Double resultado = calculadora.Division(5, 1);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(5, resultado );
    }
}
