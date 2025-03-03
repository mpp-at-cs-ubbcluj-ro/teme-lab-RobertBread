import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGuava {
    @Test
    @DisplayName("First test")
    public void testFirst() {
        ImmutableList<String> lista = ImmutableList.of("Apple", "Banana", "Cherry");
        System.out.println("Lista: " + lista);

        // Verificare condiție folosind Preconditions
        String element = "Orange";
        Preconditions.checkArgument(!lista.contains(element), "Elementul %s există deja în listă", element);
        System.out.println("Elementul nu există în listă.");
        boolean rezultat = !lista.contains(element);
    }

    @Test
    @DisplayName("Test exemplu")
    public void testExemplu() {
        assertEquals(2,2,"Ar trebui sa fie egale");
    }
}
