package kz.gairu;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Themes")
public class Theme {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "theme")
        private String value;

        
    }
