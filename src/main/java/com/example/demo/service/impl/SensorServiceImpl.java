@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public Sensor createSensor(Sensor sensor) {
        if (sensor.getSensorName() == null || sensor.getSensorName().trim().isEmpty()) {
            throw new InvalidRequestException("Sensor name cannot be empty");
        }
        if (sensor.getSensorType() == null || sensor.getSensorType().trim().isEmpty()) {
            throw new InvalidRequestException("Sensor type cannot be empty");
        }
        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor getSensorById(Long id) {
        return sensorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sensor not found with id: " + id)
                );
    }

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }
}
