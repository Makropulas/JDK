package dz3;

import java.util.Arrays;
import java.util.Iterator;

public class MyCollection<E> implements Iterable<E> {
    Object[] initialArray = {};
    private E[] array;
    private int size;
    private final MyIterator myIterator;

    @SafeVarargs
    public MyCollection(E... array) {
        this.array = array;
        size = array.length;
        myIterator = new MyIterator();
    }

    public MyCollection() {
        this.array = (E[]) initialArray;
        size = 0;
        myIterator = new MyIterator();
    }

    public void addElement(E element) {
        if (size == array.length) {
            Object[] newArray = new Object[(array.length + 1) * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[size] = element;
            array = (E[]) newArray;
        } else {
            array[size] = element;
        }
        size++;
    }

    public void removeElement(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
    }

    public void printCollection() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public Iterator<E> iterator() {
        return myIterator;
    }

    class MyIterator implements Iterator<E> {
        int index;

        public MyIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            return array[index++];
        }
    }

    public static void main(String[] args) {
        MyCollection<String> stringMyCollection = new MyCollection<>("String1", "String2", "String3");
        stringMyCollection.addElement("Add");
        stringMyCollection.addElement("New");
        stringMyCollection.addElement("Foo");
        int index = 0;
        for (String string : stringMyCollection) {
            System.out.printf("%d: %s\n", index++, string);
        }
    }
}
