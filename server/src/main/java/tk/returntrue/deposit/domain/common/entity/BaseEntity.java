package tk.returntrue.deposit.domain.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseEntity {
    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    private String createdBy;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column
    private String updatedBy;

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
