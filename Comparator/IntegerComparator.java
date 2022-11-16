package Comparator;


import usertype.IntegerClass;

import java.io.Serializable;

public class IntegerComparator implements Comparator, Serializable {
    @Override
    public int compare(Object o1, Object o2) {
        return ((IntegerClass)o1).getValue() - ((IntegerClass)o2).getValue();
    }
}