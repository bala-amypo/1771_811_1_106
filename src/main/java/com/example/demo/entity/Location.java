import java.time.LocalDateTime;
public class Location{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String locationName;
    private String description;
    private String region;
    private LocalDateTime installedAt;
    private Boolean isActive;

    public Location(Long id,String locationName,String description,String region,)
}