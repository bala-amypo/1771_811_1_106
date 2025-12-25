@Service
public class ComplianceThresholdServiceImpl implements ComplianceThresholdService {

    @Autowired
    private ComplianceThresholdRepository thresholdRepository;

    @Override
    public ComplianceThreshold createThreshold(ComplianceThreshold threshold) {

        if (threshold.getSensorType() == null || threshold.getSensorType().trim().isEmpty()) {
            throw new InvalidRequestException("Sensor type cannot be empty");
        }

        if (threshold.getMinValue() == null || threshold.getMaxValue() == null) {
            throw new InvalidRequestException("Threshold values cannot be null");
        }

        if (threshold.getMinValue() >= threshold.getMaxValue()) {
            throw new InvalidRequestException("Min value must be less than max value");
        }

        return thresholdRepository.save(threshold);
    }

    @Override
    public ComplianceThreshold getBySensorType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new InvalidRequestException("Sensor type cannot be empty");
        }

        return thresholdRepository.findBySensorType(type)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No threshold found for sensor type: " + type));
    }

    @Override
    public List<ComplianceThreshold> getAllThresholds() {
        return thresholdRepository.findAll();
    }
}
