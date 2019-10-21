package it.unifi.webapp.backend.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Expose
    @Column( unique = true )
    private String uuid;

    protected BaseEntity() {
    }

    public BaseEntity( String uuid ) {
        if ( uuid == null ) {
            throw new IllegalArgumentException( "uuid cannot be null" );
        }
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return getId().equals(that.getId()) &&
                getUuid().equals(that.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUuid());
    }
}
