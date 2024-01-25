package ru.alox1d.androidcore.interviews.wb;

import java.util.Objects;

public class EqualsHashcode {
    public static void main(String[] args) {

    }

    public class MapValueClass {

        // проблема в поле, что он не финально, можно менять в рантайме -> сделать final
        private String value;

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;
            return ((MapValueClass) obj).value.equals(value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}

