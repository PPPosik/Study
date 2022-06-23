package jpabook.start1.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
//@org.hibernate.annotations.DynamicUpdate
@Table(name = "member", uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames = {"name", "age"})})
public class Member {
    @Id
    @Column(name = "id", length = 100) // Specified key was too long; max key length is 1000 bytes, default 255에서 인코딩 방식에 따라 255 * n bytes로 계산됨
    private String id;

    @Column(name = "name", nullable = false, length = 10)
    private String username;

    // 기본 매핑 사용
    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
