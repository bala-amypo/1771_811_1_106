@Service
public class SensorReadingServiceImpl implements SensorReadingService {

    @Autowired
    private SensorReadingRepository readingRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public SensorReading createReading(SensorReading reading) {

        if (reading.getReadingValue() == null) {
            throw new InvalidRequestException("Reading value cannot be null");
        }

        if (reading.getSensor() == null || reading.getSensor().getId() == null) {
            throw new InvalidRequestException("Sensor ID is required");
        }

        Sensor sensor = sensorRepository.findById(reading.getSensor().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sensor not found with id: " + reading.getSensor().getId()));

        reading.setSensor(sensor);
        reading.setTimestamp(LocalDateTime.now());

        return readingRepository.save(reading);
    }

    @Override
    public List<SensorReading> getReadingsBySensor(Long sensorId) {
        if (sensorId == null) {
            throw new InvalidRequestException("Sensor ID cannot be null");
        }

        sensorRepository.findById(sensorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sensor not found with id: " + sensorId));

        return readingRepository.findBySensorId(sensorId);
    }
}
