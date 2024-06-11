package Sber.Sber.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@Builder

@AllArgsConstructor
@Entity
@Table(name = "realtys")

public class Realty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "realtyname")
    private String realtyname;

    @Column(name = "sellerId")
    private Long sellerId;

    @Column(name = "price")
    private Double price;

    @Column(name = "floor")
    private Double floor;

    @Column(name = "allfloor")
    private Double allfloor;

    @Column(name = "square")
    private Double square;

    @Column(name = "number_rooms")
    private Double numberRooms;

    @Column(name = "type")
    private String type;

    @Column(name = "adress")
    private String adress;

    @Column(name = "year")
    private Integer year;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "company_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Company companyForRealty;

    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Client client;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "realty")
    private List<Image> images;
    private Long previewImageId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    public Realty() {
        this.realtyname = realtyname;
    }

//    public void setClient(Client client) { // add setter method
//        this.client = client;
//    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addImageToProduct(Image image){
        image.setRealty(this);
        images.add(image);
    }
}
