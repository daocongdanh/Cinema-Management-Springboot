package com.example.cinemamanagement.models;

import com.example.cinemamanagement.enums.MovieStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "running_time")
    private int runningTime;

    @Column(name = "country")
    private String country;

    @Column(name = "language")
    private String language;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "author")
    private String author;

    @Column(name = "poster")
    private String poster;

    @Column(name = "trailer")
    private String trailer;

    @Enumerated(EnumType.STRING)
    @Column(name = "movie_status")
    private MovieStatus movieStatus;

    @ManyToOne
    @JoinColumn(name = "movie_type_id", nullable = false)
    private MovieType movieType;
}
