public class ComplianceLog{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column()
    private String locationName;
    private String sensorType;
    private 
}