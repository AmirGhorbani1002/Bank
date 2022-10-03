package base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate createDate;
    private LocalDate updateDate;

    @PrePersist
    private void whenCreate() {
        createDate = LocalDate.now();
    }

    @PreUpdate
    public void whenUpdate() {
        updateDate = LocalDate.now();
    }

    public Boolean isNow() {
        return id == null;
    }

}
