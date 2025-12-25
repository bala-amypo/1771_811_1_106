@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    @Autowired
    private ComplianceThresholdRepository thresholdRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorReadingRepository readingRepository;

    @Autowired
    private ComplianceLogRepository logRepository;

    @Override
    public ComplianceLog evaluateReading(Long readingId) {

        SensorReading reading = readingRepository.findById(readingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reading not found with id: " + readingId));

        String sensorType = reading.getSensor().getSensorType();

        ComplianceThreshold threshold = thresholdRepository.findBySensorType(sensorType)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No threshold found for sensor type: " + sensorType));

        boolean isCompliant =
                reading.getReadingValue() >= threshold.getMinValue() &&
                        reading.getReadingValue() <= threshold.getMaxValue();

        ComplianceLog log = new ComplianceLog();
        log.setSensor(reading.getSensor());
        log.setReading(reading);
        log.setCompliant(isCompliant);
        log.setTimestamp(LocalDateTime.now());

        return logRepository.save(log);
    }
}
