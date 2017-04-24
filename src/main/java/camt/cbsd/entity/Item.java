package camt.cbsd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Biggy's Dell Laptop on 17-Apr-17.
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull long id;
    @NonNull String name;
    @NonNull String des;
    @NonNull String picture;
    @NonNull int amount;
    @NonNull double rating;
    @NonNull double price;
}
