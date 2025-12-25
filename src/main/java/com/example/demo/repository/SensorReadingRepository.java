public interface SensorReadingRepository
        extends JpaRepository<SensorReading, Long> {

    List<SensorReading> findBySensor_Id(Long sensorId);
}
