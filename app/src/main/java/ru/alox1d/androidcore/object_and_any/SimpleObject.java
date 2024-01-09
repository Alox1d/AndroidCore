package ru.alox1d.androidcore.object_and_any;

public class SimpleObject {
    static class CloneByObject {
        public CloneByObject foo() {
            try {
                return (CloneByObject) clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    static class CloneByInterface implements Cloneable {

        @Override
        public CloneByInterface clone() {
            try {
                return (CloneByInterface) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    public static void main(String[] args) {
        CloneByInterface c = new CloneByInterface();
        CloneByInterface cloned = c.clone();
        System.out.print(cloned.toString());

        CloneByObject original2 = new CloneByObject();
        CloneByObject cloneByObject = original2.foo();
        System.out.print(cloneByObject.toString());

    }
}
