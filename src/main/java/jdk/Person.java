package jdk;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zscome
 * DateTime: 2020-05-26 21:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name="test";

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
