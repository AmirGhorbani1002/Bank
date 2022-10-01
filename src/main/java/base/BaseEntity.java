package base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @PrePersist
    private void whenCreate(){
        createDate = LocalDateTime.now();
    }

    @PreUpdate
    public void whenUpdate(){
        updateDate = LocalDateTime.now();
    }

}
