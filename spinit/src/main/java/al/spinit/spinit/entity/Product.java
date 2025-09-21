package al.spinit.spinit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "track_list", nullable = false)
    private String trackList;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "image", nullable = false)
    private String image;
    @ManyToMany
    @Column(name = "genre", nullable = false)
    private Set<Genre> genre = new HashSet<>();
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Long createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Long updatedAt;
}
