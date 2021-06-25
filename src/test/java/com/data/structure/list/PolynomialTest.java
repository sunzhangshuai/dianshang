package com.data.structure.list;

import com.data.structure.list.impl.PolynomialImpl;
import com.data.structure.list.model.Term;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PolynomialTest {
    PolynomialImpl polynomial;
    PolynomialImpl addPolynomial;

    @Before
    public void test() {
        List<Term> terms = new ArrayList<Term>();
        terms.add(new Term(1, 1));
        terms.add(new Term(2, 5));
        terms.add(new Term(2, 4));
        terms.add(new Term(1, 4));

        polynomial = new PolynomialImpl(terms);
        Assert.assertEquals("1.0X^1+3.0X^4+2.0X^5", polynomial.toString());


        List<Term> addTerms = new ArrayList<Term>();
        addTerms.add(new Term(-1, 1));
        addTerms.add(new Term(-3, 5));
        addTerms.add(new Term(3, 6));
        addPolynomial = new PolynomialImpl(addTerms);

        Assert.assertEquals("-1.0X^1-3.0X^5+3.0X^6", addPolynomial.toString());
    }

    @Test
    public void operate() throws CloneNotSupportedException {
        polynomial.addPolyn(addPolynomial);
        Assert.assertEquals("3.0X^4-1.0X^5+3.0X^6", polynomial.toString());
        polynomial.subtractPolyn(addPolynomial);
        Assert.assertEquals("1.0X^1+3.0X^4+2.0X^5", polynomial.toString());
        polynomial.multiplyPolyn(addPolynomial);
        Assert.assertEquals("-1.0X^2-3.0X^5-5.0X^6+3.0X^7-9.0X^9+3.0X^10+6.0X^11", polynomial.toString());
    }
}
