package kr.ac.dankook.army.dto.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Planner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL, mappedBy = "planner")
    private List<PlannerComponent> plannerComponentList = new ArrayList<>();
}
