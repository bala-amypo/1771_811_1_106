import java.time.LocalDateTime;
public class Location{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String locationName;
    private String description;
    private String region;
    private LocalDateTime createdAt;

    public Location(Long id,String locationName,String description,String region,LocalDateTime createdAt){
        this.id=id;
        this.locationName=locationName;
        this.description=description;
        this.region=region;
        this.createdAt=createdAt;
    }
    public Location(){
     
    }
    public void locationName(String locationName){
        this.locationName=locationName;
    }
    public String locationName(){
        return this.locationName;
    }
     public void description(String description){
        this.description=description;
    }
    public String description(){
        return this.description;
    }
     public void region(String region){
        this.region=region;
    }
    public String region(){
        return this.region;
    }
     public LocalDateTime createdAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }
    public String locationName(){
        return this.locationName;
    }
    }
