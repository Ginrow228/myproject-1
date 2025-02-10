package lesson18.task6;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Camp {
    private List<Boyscout> boyscouts;

    public Camp(List<Boyscout> boyscouts) {
        this.boyscouts = boyscouts;
    }

    public Map<Team, List<Boyscout>> split(){
        return boyscouts.stream()
                .collect(Collectors.groupingBy(Boyscout::getTeam,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingInt(Boyscout::getAge).reversed())
                                        .collect(Collectors.toList()))
                ));

    }
}
